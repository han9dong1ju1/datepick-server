package app.hdj.datepick.data.query;

import app.hdj.datepick.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaUserRepository extends JpaRepository<User, Long> {

    Boolean existsByUid(String uid);

}
