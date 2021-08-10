package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();
    public User findById(Long id);

    public User create(User user);
    public User update(User user);
    public Boolean delete(User user);
}
