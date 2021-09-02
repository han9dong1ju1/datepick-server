package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);

    User addUser(User user);
    User modifyUser(User user);
    void removeUser(Long id);
}
