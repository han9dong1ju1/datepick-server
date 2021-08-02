package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.UserTable;
import app.hdj.datepick.data.query.JpaUserRepository;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRepositoryImp implements UserRepository {

    private ModelMapper mapper = new ModelMapper();

    private JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImp(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User getUser(Long id) {
        UserTable userTable = jpaUserRepository.findById(id).orElseThrow();
        return mapper.map(userTable, User.class);
    }

    @Override
    public void createUser(User user) {
        jpaUserRepository.save(mapper.map(user, UserTable.class));
    }

    @Override
    public void updateUser(User user) {
        jpaUserRepository.save(mapper.map(user, UserTable.class));
    }

    @Override
    public void deleteUser(User user) {
        jpaUserRepository.delete(mapper.map(user, UserTable.class));
    }

}
