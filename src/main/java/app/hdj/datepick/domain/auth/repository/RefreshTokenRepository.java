package app.hdj.datepick.domain.auth.repository;

import app.hdj.datepick.domain.auth.entity.RefreshToken;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUuidAndExpireAtAfter(String uuid, LocalDateTime now);
}
