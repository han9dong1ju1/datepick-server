package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository {

    List<User> findAll();
    User findById(Long id);
    List<User> findByIdList(List<Long> idList);

    User create(User user);
    User update(User user);
    void delete(Long id);

}
