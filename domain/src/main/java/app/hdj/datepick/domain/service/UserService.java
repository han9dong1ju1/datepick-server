package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);

    User addUser(User user);
    User modifyUser(User user);
    void removeUser(Long id);
}
