package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.user.entity.User;

import java.time.LocalDateTime;

public interface RefreshTokenService {
    void saveToken(String token, User user, String uuid, LocalDateTime expireAt);
    Long getUserIdByToken(String token);
    void deactivateToken(Long userId, String uuid);
}
