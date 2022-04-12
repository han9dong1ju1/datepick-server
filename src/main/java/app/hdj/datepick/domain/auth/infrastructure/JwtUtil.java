package app.hdj.datepick.domain.auth.infrastructure;

import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final JwtParser jwtParser;
    private final Duration accessTokenExpireInterval;
    private final Duration refreshTokenExpireInterval;

    public JwtUtil(@Value("${security.jwt.secret-key}") String secretKey,
                   @Value("${security.jwt.access-token.expire-interval}") Duration accessTokenExpireInterval,
                   @Value("${security.jwt.refresh-token.expire-interval}") Duration refreshTokenExpireInterval) {
        this.accessTokenExpireInterval = accessTokenExpireInterval;
        this.refreshTokenExpireInterval = refreshTokenExpireInterval;

        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createAccessToken(Map<String, Object> payload, LocalDateTime issuedAt) {
        return Jwts.builder()
                .setIssuedAt(Timestamp.valueOf(issuedAt))
                .setExpiration(Timestamp.valueOf(getAccessTokenExpireAt(issuedAt)))
                .addClaims(payload)
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Map<String, Object> payload, LocalDateTime issuedAt) {
        return Jwts.builder()
                .setIssuedAt(Timestamp.valueOf(issuedAt))
                .setExpiration(Timestamp.valueOf(getRefreshTokenExpireAt(issuedAt)))
                .addClaims(payload)
                .signWith(key)
                .compact();
    }

    private LocalDateTime getAccessTokenExpireAt(LocalDateTime issuedAt) {
        return issuedAt.plusSeconds(accessTokenExpireInterval.toSeconds());
    }

    public LocalDateTime getRefreshTokenExpireAt(LocalDateTime issuedAt) {
        return issuedAt.plusSeconds(refreshTokenExpireInterval.toSeconds());
    }

    public Map<String, Object> getPayload(String token) {
        log.debug("'getPayload' invoked");
        if (token == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }
    }

    public long getAccessTokenExpireIntervalInSeconds() {
        return accessTokenExpireInterval.toSeconds();
    }

}
