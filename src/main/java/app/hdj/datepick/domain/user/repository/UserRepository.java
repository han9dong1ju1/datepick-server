package app.hdj.datepick.domain.user.repository;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderAndUid(Provider provider, String uid);

}
