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

class JwtUtilTest {

    private static final String secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
    private static final Duration accessTokenExpireInterval = Duration.ofHours(1L);
    private static final Duration refreshTokenExpireInterval = Duration.ofDays(30L);

    private JwtUtil jwtUtil;
    private JwtParser jwtParser;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secretKey, accessTokenExpireInterval, refreshTokenExpireInterval);
        jwtParser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))).build();
    }

    @Test
    @DisplayName("만료 시간이 올바른 Access Token을 생성한다.")
    void createAccessTokenWithExpiration() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        String accessToken = jwtUtil.createAccessToken(Map.of(), now);

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

        // when
        String accessToken = jwtUtil.createRefreshToken(Map.of(), now);

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
        String accessToken = jwtUtil.createAccessToken(payload, now);

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
        String accessToken = jwtUtil.createAccessToken(Map.of(), now);

        // when
        Map<String, Object> tokenPayload = jwtUtil.getPayload(accessToken);

        // then
        assertThat(tokenPayload).isNotEmpty();
    }

    @Test
    @DisplayName("유효한 Token의 유효성을 검사한다.")
    void validateValidToken() {
        // given
        LocalDateTime now = LocalDateTime.now();
        String accessToken = jwtUtil.createAccessToken(Map.of(), now);

        // when, then
        assertDoesNotThrow(() -> jwtUtil.getPayload(accessToken));
    }

    @Test
    @DisplayName("만료된 Token의 유효성을 검사하면 예외를 발생시킨다.")
    void validateExpiredToken() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(accessTokenExpireInterval.plusSeconds(1L).toSeconds());
        String accessToken = jwtUtil.createAccessToken(Map.of(), now);

        // when
        CustomException throwable = catchThrowableOfType(() -> jwtUtil.getPayload(accessToken), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.TOKEN_EXPIRED);
    }

    @Test
    @DisplayName("유효하지 않은 Token의 유효성을 검사하면 예외를 발생시킨다..")
    void validateInvalidToken() {
        // given
        LocalDateTime now = LocalDateTime.now().minusSeconds(accessTokenExpireInterval.plusSeconds(1L).toSeconds());
        String accessToken = jwtUtil.createAccessToken(Map.of(), now) + "Modify Token String Value";

        // when
        CustomException throwable = catchThrowableOfType(() -> jwtUtil.getPayload(accessToken), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.TOKEN_INVALID);
    }

}