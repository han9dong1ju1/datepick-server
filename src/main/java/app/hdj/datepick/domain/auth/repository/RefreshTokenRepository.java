package app.hdj.datepick.domain.auth.repository;

import app.hdj.datepick.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndUserId(String token, Long userId);
    Optional<RefreshToken> findByTokenAndExpireAtAfter(String token, LocalDateTime now);
    Optional<RefreshToken> findByUserIdAndUuid(Long userId, String uuid);
}
