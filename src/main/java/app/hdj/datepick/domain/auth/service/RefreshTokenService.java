package app.hdj.datepick.domain.auth.service;

import java.time.LocalDateTime;

public interface RefreshTokenService {

    void saveToken(String token, Long userId, LocalDateTime expireAt);
    Long getUserIdByToken(String token);
    void deactivateToken(String token, Long userId);
}
