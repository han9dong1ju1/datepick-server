package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.table.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUid(String uid);

}
