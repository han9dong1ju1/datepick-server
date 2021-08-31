package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.query.JpaUserRepository;
import app.hdj.datepick.domain.entity.User;
import app.hdj.datepick.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImp implements UserRepository {

    /**
     * Entity to DTO Mapper
     * (참고: https://stackoverflow.com/questions/47929674/modelmapper-mapping-list-of-entites-to-list-of-dto-objects/58324819)
     */
    private final JpaUserRepository jpaUserRepository;

    @Override
    public List<User> findAll(){
        return null;
    }

    @Override
    public User findById(Long id){
        //UserTable to User
        return null;
    }

    @Override
    public List<User> findByIdList(List<Long> idList){
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user){
        return null;
    }

    @Override
    public void delete(Long id){

    }

}
