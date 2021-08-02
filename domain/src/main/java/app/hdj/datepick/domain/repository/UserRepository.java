package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User getUser(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
