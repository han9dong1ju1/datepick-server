package app.hdj.datepick.domain.auth.repository;

import app.hdj.datepick.domain.auth.entity.UserUnregisterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUnregisterLogRepository extends JpaRepository<UserUnregisterLog, Long> {

}
