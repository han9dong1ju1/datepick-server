package app.hdj.datepick.domain.auth.infrastructure;

import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class JwtUtils {

    private final Key key;
    private final JwtParser jwtParser;
    private final Duration accessTokenExpireInterval;
    private final Duration refreshTokenExpireInterval;

    public JwtUtils(@Value("${security.jwt.secret-key}") String secretKey,
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
                .addClaims(Jwts.claims(payload))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(LocalDateTime issuedAt) {
        return Jwts.builder()
                .setIssuedAt(Timestamp.valueOf(issuedAt))
                .setExpiration(Timestamp.valueOf(issuedAt.plusSeconds(refreshTokenExpireInterval.toSeconds())))
                .signWith(key)
                .compact();
    }

    public void validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }
    }

    public Map<String, Object> getPayload(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public LocalDateTime getAccessTokenExpireAt(LocalDateTime issuedAt) {
        return issuedAt.plusSeconds(accessTokenExpireInterval.toSeconds());
    }

    public long getAccessTokenExpireIntervalInSeconds() {
        return accessTokenExpireInterval.toSeconds();
    }

}
