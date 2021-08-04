package app.hdj.datepick.data.Service;

import app.hdj.datepick.data.repository.UserRepositoryImp;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();
    public User findById(Long id);
    public User findByNickname(String nickname);
    public List<User> findByIdList(List<Long> idList);

    public Boolean create(User user);
    public User update(User user);
    public Boolean delete(User user);
}
