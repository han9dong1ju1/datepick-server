package app.hdj.datepick.domain.user.repository;

import app.hdj.datepick.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSupportRepository {

    List<User> findAll();
    Optional<User> findById(Long id);
}
