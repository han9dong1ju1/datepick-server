package app.hdj.datepick.data.Service;

import app.hdj.datepick.data.repository.UserRepositoryImp;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    //User Repository
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public List<User> findByIdList(List<Long> idList) {
        return userRepository.findByIdList(idList);
    }

    @Override
    public Boolean create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public Boolean delete(User user) {
        return userRepository.delete(user);
    }
}
