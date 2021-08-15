package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.model.User;

import java.util.List;


public interface UserRepository {

    public List<User> findAll();
    public User findById(Long id);
    public List<User> findByIdList(List<Long> idList);

    public User create(User user);
    public User update(User user);
    public Boolean delete(User user);

}
