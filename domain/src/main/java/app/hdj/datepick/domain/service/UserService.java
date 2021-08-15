package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(Long id);

    public User create(User user);
    public User update(User user);
    public Boolean delete(User user);
}
