package app.hdj.datepick.domain.user.repository;

import app.hdj.datepick.domain.user.entity.UserUnregisterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUnregisterLogRepository extends JpaRepository<UserUnregisterLog, Long> {

}
