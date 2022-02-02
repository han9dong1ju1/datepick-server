package app.hdj.datepick.domain.user.repository;

import app.hdj.datepick.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUid(String uid);

    Optional<User> findUserByIsDeletedFalseAndIsBannedFalseAndId(Long id);

}
