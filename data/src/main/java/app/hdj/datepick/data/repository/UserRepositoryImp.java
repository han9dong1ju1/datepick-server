package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.UserEntity;
import app.hdj.datepick.data.query.JpaUserRepository;
import app.hdj.datepick.domain.model.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Repository
public class UserRepositoryImp implements UserRepository {

    /**
     * Entity to DTO Mapper
     * (참고: https://stackoverflow.com/questions/47929674/modelmapper-mapping-list-of-entites-to-list-of-dto-objects/58324819)
     */
    private final ModelMapper mapper = new ModelMapper();

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImp(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> findAll(){
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> users = mapper.map(userEntities, listType);
        return users;
    }

    @Override
    public User findById(Long id){
        //UserTable to User
        return mapper.map(
                jpaUserRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d 의 유저가 존재하지 않습니다", id))),
                User.class
        );
    }

    @Override
    public List<User> findByIdList(List<Long> idList){
        List<User> users = idList.stream()
                .map(this::findById)
                .map(userTable -> mapper.map(userTable, User.class))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public User create(User user) {
        if (jpaUserRepository.existsByUid(user.getUid())) {
            throw new NoSuchElementException(String.format("해당 uid : %s의 유저는 이미 존재합니다.", user.getUid()));
        }
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        jpaUserRepository.save(userEntity);
        return user;
    }

    @Override
    public User update(User user){
        UserEntity userEntity = jpaUserRepository.findById(user.getId()).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d의 유저가 존재하지 않습니다", user.getId())));
        mapper.map(user, userEntity);
        jpaUserRepository.save(userEntity);
        return mapper.map(userEntity, User.class);
    }

    @Override
    public void delete(Long id){
        UserEntity userEntity = jpaUserRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d의 유저가 존재하지 않습니다", id)));
        jpaUserRepository.delete(userEntity);
    }

}
