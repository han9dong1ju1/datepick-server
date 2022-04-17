package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.entity.RefreshToken;
import app.hdj.datepick.domain.auth.infrastructure.oauth.OAuthUserInfo;
import app.hdj.datepick.domain.auth.infrastructure.JwtUtil;
import app.hdj.datepick.domain.auth.infrastructure.oauth.OAuthHandler;
import app.hdj.datepick.domain.auth.repository.RefreshTokenRepository;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import app.hdj.datepick.global.util.HashingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuthHandler oAuthHandler;
    private final JwtUtil jwtUtil;

    public static final String USER_ID_CLAIM_KEY = "user_id";
    public static final String TOKEN_UUID_CLAIM_KEY = "uuid";
    public static final String USER_AUTHORITIES_CLAIM_KEY = "authorities";

    @Transactional
    public AllTokenResponse signInByOAuth(final Provider provider, final String code) {
        OAuthUserInfo userInfo = oAuthHandler.getUserInfo(provider, code);
        User user = userRepository.findByProviderAndUid(userInfo.getProvider(), userInfo.getUid())
                .orElseGet(() -> signUp(userInfo));
        return createNewTokens(user);
    }

    private AllTokenResponse createNewTokens(User user) {
        LocalDateTime now = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString();
        String accessToken = jwtUtil.createAccessToken(createAccessTokenPayload(user, uuid), now);
        String refreshToken = jwtUtil.createRefreshToken(createRefreshTokenPayload(uuid), now);
        RefreshToken token = RefreshToken.builder()
                .token(HashingUtil.hash(refreshToken))
                .userId(user.getId())
                .uuid(uuid)
                .expireAt(jwtUtil.getRefreshTokenExpireAt(now))
                .build();
        refreshTokenRepository.save(token);
        return new AllTokenResponse(accessToken, jwtUtil.getAccessTokenExpireIntervalInSeconds(), refreshToken);
    }

    @Transactional
    public AllTokenResponse refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByUuidAndExpireAtAfter(getUuidFromToken(token), LocalDateTime.now())
                .orElseThrow(() -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        return updateToken(refreshToken);
    }

    private AllTokenResponse updateToken(RefreshToken token) {
        User user = userRepository.findById(token.getUserId()).orElseThrow();
        LocalDateTime now = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString();
        String accessToken = jwtUtil.createAccessToken(createAccessTokenPayload(user, uuid), now);
        String refreshToken = jwtUtil.createRefreshToken(createRefreshTokenPayload(uuid), now);
        token.setToken(HashingUtil.hash(refreshToken));
        token.setUserId(user.getId());
        token.setUuid(uuid);
        token.setExpireAt(jwtUtil.getRefreshTokenExpireAt(now));
        refreshTokenRepository.save(token);
        return new AllTokenResponse(accessToken, jwtUtil.getAccessTokenExpireIntervalInSeconds(), refreshToken);
    }

    private Map<String, Object> createAccessTokenPayload(User user, String uuid) {
        return Map.of(USER_ID_CLAIM_KEY, user.getId(),
                USER_AUTHORITIES_CLAIM_KEY, List.of(Role.USER.toString()),
                TOKEN_UUID_CLAIM_KEY, uuid);
    }

    private Map<String, Object> createRefreshTokenPayload(String uuid) {
        return Map.of(TOKEN_UUID_CLAIM_KEY, uuid);
    }

    private User signUp(OAuthUserInfo userInfo) {
        User user = createUserByUserInfo(userInfo);
        return userRepository.save(user);
    }

    private User createUserByUserInfo(OAuthUserInfo userInfo) {
        return User.builder()
                .uid(userInfo.getUid())
                .provider(userInfo.getProvider())
                .email(userInfo.getEmail())
                .nickname(createNickname(userInfo.getName()))
                .gender(userInfo.getGender())
                .build();
    }

    private String createNickname(String name) {
        String prefix = Objects.requireNonNullElse(name, "user");
        name = prefix + (int) (Math.random() * Math.pow(10, 11));
        if (name.length() > 16) {
            name = name.substring(0, 15);
        }
        return name;
    }

    @Transactional
    public void signOut(Long userId, String uuid) {
        refreshTokenRepository.findByUuidAndExpireAtAfter(uuid, LocalDateTime.now())
                .ifPresent(refreshToken -> {
                    if (!refreshToken.getUserId().equals(userId)) {
                        throw new CustomException(ErrorCode.TOKEN_MALFORMED);
                    }
                    refreshToken.setExpireAt(LocalDateTime.now());
                });
    }

    public Long getUserIdFromToken(String token) {
        Map<String, Object> payload = jwtUtil.getPayload(token);
        return Long.valueOf(String.valueOf(payload.get(USER_ID_CLAIM_KEY)));
    }

    public String getUuidFromToken(String token) {
        Map<String, Object> payload = jwtUtil.getPayload(token);
        return String.valueOf(payload.get(TOKEN_UUID_CLAIM_KEY));
    }

}
