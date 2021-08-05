package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository {

    public List<User> findAll();
    public Page<User> findAll(Pageable pageable);

    public User findById(Long id);
    public List<User> findByIdList(List<Long> idList);

    public Boolean create(User user);
    public User update(User user);
    public Boolean delete(User user);

}
