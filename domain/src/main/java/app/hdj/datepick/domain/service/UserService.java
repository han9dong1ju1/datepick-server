package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();
    public Page<User> findAll(Pageable pageable);
    public User findById(Long id);
    public List<User> findByIdList(List<Long> idList);

    public Boolean create(User user);
    public User update(User user);
    public Boolean delete(User user);
}
