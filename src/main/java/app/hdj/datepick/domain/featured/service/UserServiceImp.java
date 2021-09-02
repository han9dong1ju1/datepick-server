package app.hdj.datepick.domain.featured.service;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User addUser(User user) {
        return null;
        //return userRepository.create(user);
    }

    @Override
    public User modifyUser(User user) {
        return null;
        //return userRepository.update(user);
    }

    @Override
    public void removeUser(Long id) {
        //userRepository.delete(id);
    }
}
