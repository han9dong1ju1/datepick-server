package app.hdj.datepick.domain.user.repository;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.enums.Provider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderAndUid(Provider provider, String uid);
}
