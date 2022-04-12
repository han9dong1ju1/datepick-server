package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.domain.auth.infrastructure.JwtUtils;
import app.hdj.datepick.domain.auth.infrastructure.oauth.OAuthHandler;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final OAuthHandler oAuthHandler;
    private final JwtUtils jwtUtils;

    private static final String USER_ID_CLAIM_KEY = "id";
    private static final String USER_AUTHORITIES_CLAIM_KEY = "authorities";

    @Transactional
    public AllTokenResponse signIn(final Provider provider, final String code) {
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
        Map<String, Object> payload = createPayloadByUser(user);
        String accessToken = jwtUtils.createAccessToken(payload, now);
        String refreshToken = jwtUtils.createRefreshToken(now);
        refreshTokenService.saveToken(refreshToken, user.getId(), jwtUtils.getAccessTokenExpireAt(now));
        return new AllTokenResponse(accessToken, jwtUtils.getAccessTokenExpireIntervalInSeconds(), refreshToken);
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

    private Map<String, Object> createPayloadByUser(User user) {
        return Map.of(USER_ID_CLAIM_KEY, user.getId(), USER_AUTHORITIES_CLAIM_KEY, List.of("USER"));
    }

    @Transactional
    public void signOut(String token, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        refreshTokenService.deactivateToken(token, userId);
    }

    public Long getUserIdFromToken(String token) {
        Map<String, Object> payload = jwtUtils.getPayload(token);
        return (Long) payload.get(USER_ID_CLAIM_KEY);
    }

}
