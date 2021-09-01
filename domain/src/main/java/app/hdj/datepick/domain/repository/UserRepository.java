package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.entity.table.User;
import app.hdj.datepick.domain.repository.support.UserSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSupportRepository {

    List<User> findAll();
    Optional<User> findById(Long id);
}
