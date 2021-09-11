package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User addUser(User user) {
        return null;
        //return userRepository.create(user);
    }

    public User modifyUser(User user) {
        return null;
        //return userRepository.update(user);
    }

    public void removeUser(Long id) {
        //userRepository.delete(id);
    }
}
