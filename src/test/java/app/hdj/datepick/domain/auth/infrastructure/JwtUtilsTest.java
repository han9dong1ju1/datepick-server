package app.hdj.datepick.domain.auth.infrastructure;

import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;

class JwtUtilsTest {

    private final String secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
    private final Duration accessTokenExpireInterval = Duration.ofHours(1L);
    private final Duration refreshTokenExpireInterval = Duration.ofDays(30L);

    private JwtUtils jwtUtils;
    private JwtParser jwtParser;

    @BeforeEach()
    void before() {
        jwtUtils = new JwtUtils(secretKey, accessTokenExpireInterval, refreshTokenExpireInterval);
        jwtParser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))).build();
    }

    @Test
    @DisplayName("만료 시간이 올바른 Access Token을 생성한다.")
    void createAccessTokenWithExpiration() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        String accessToken = jwtUtils.createAccessToken(Map.of(), now);

        // then
        long expireAt = jwtParser.parseClaimsJws(accessToken).getBody().getExpiration().toInstant().getEpochSecond();
        long calculatedExpiration = Timestamp.valueOf(now.plusSeconds(accessTokenExpireInterval.toSeconds())).toInstant().getEpochSecond();
        assertThat(expireAt).isEqualTo(calculatedExpiration);
    }

    @Test
    @DisplayName("만료 시간이 올바른 Refresh Token을 생성한다.")
    void createRefreshTokenWithExpiration() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> payload = Map.of();

        // when
        String accessToken = jwtUtils.createRefreshToken(now);

        // then
        long expireAt = jwtParser.parseClaimsJws(accessToken).getBody().getExpiration().toInstant().getEpochSecond();
        long calculatedExpiration = Timestamp.valueOf(now.plusSeconds(refreshTokenExpireInterval.toSeconds())).toInstant().getEpochSecond();
        assertThat(expireAt).isEqualTo(calculatedExpiration);
    }

    @Test
    @DisplayName("Payload를 담은 Token을 생성한다.")
    void createTokenWithPayload() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Long longValue = 1L;
        List<String> listValue = List.of("USER");
        Map<String, Object> payload = Map.of("long", longValue, "list", listValue);

        // when
        String accessToken = jwtUtils.createAccessToken(payload, now);

        // then
        Claims tokenPayload = jwtParser.parseClaimsJws(accessToken).getBody();
        assertThat(tokenPayload.get("long", Long.class)).isEqualTo(longValue);
        assertThat(tokenPayload.get("list", List.class)).isEqualTo(listValue);
    }

    @Test
    @DisplayName("Token으로부터 Payload를 가져온다.")
    void getTokenPayload() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> payload = Map.of();
        String accessToken = jwtUtils.createAccessToken(payload, now);

        // when
        Map<String, Object> tokenPayload = jwtUtils.getPayload(accessToken);

        // then
        assertThat(tokenPayload).isNotEmpty();
    }

    @Test
    @DisplayName("유효한 Token의 유효성을 검사한다.")
    void validateValidToken() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> payload = Map.of();
        String accessToken = jwtUtils.createAccessToken(payload, now);

        // when, then
        assertDoesNotThrow(() -> jwtUtils.validateToken(accessToken));
    }

    @Test
    @DisplayName("만료된 Token의 유효성을 검사한다.")
    void validateExpiredToken() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(accessTokenExpireInterval.plusSeconds(1L).toSeconds());
        Map<String, Object> payload = Map.of();
        String accessToken = jwtUtils.createAccessToken(payload, now);

        // when
        CustomException throwable = catchThrowableOfType(() -> jwtUtils.validateToken(accessToken), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.TOKEN_EXPIRED);
    }

    @Test
    @DisplayName("유효하지 않은 Token의 유효성을 검사한다.")
    void validateInvalidToken() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(accessTokenExpireInterval.plusSeconds(1L).toSeconds());
        Map<String, Object> payload = Map.of();
        String accessToken = jwtUtils.createAccessToken(payload, now) + "Modify Token String Value";

        // when
        CustomException throwable = catchThrowableOfType(() -> jwtUtils.validateToken(accessToken), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.TOKEN_INVALID);
    }

}