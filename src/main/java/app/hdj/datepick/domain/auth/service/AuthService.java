package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.infrastructure.oauth.OAuthUserInfo;
import app.hdj.datepick.domain.auth.infrastructure.JwtUtil;
import app.hdj.datepick.domain.auth.infrastructure.oauth.OAuthHandler;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.domain.user.enums.Role;
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
    private final RefreshTokenService refreshTokenService;
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
        return createTokens(user);
    }

    @Transactional
    public AllTokenResponse refreshToken(String token) {
        Long userId = refreshTokenService.getUserIdByToken(token);
        User user = userRepository.findById(userId).orElseThrow();
        return createTokens(user);
    }

    private AllTokenResponse createTokens(User user) {
        LocalDateTime now = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString();
        String accessToken = jwtUtil.createAccessToken(createAccessTokenPayload(user, uuid), now);
        String refreshToken = jwtUtil.createRefreshToken(createRefreshTokenPayload(uuid), now);
        refreshTokenService.saveToken(refreshToken, user, uuid, jwtUtil.getRefreshTokenExpireAt(now));
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
        refreshTokenService.deactivateToken(userId, uuid);
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
