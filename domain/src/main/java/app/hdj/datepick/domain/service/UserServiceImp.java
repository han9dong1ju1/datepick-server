package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.create(user);
    }

    @Override
    public User modifyUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.delete(id);
    }
}
