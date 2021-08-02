package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserTable, Long> {

}
