package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.dto.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository {

    public List<User> findAll();
    public User findById(Long id);
    public User findByNickname(String nickname);
    public List<User> findByIdList(List<Long> idList);

    public Boolean create(User user);
    public User update(User user);
    public Boolean delete(User user);

}
