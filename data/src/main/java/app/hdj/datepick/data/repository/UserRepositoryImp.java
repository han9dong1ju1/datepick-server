package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.UserTable;
import app.hdj.datepick.data.query.JpaUserRepository;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImp implements UserRepository {

    private final ModelMapper mapper = new ModelMapper();

    private final JpaUserRepository jpaUserRepository;

    @Autowired //Jpa Repositiory 주입.
    public UserRepositoryImp(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> findAll(){
        //https://stackoverflow.com/questions/47929674/modelmapper-mapping-list-of-entites-to-list-of-dto-objects/58324819 참고
        List<UserTable> userTables = jpaUserRepository.findAll();
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> postDtoList = mapper.map(userTables, listType);
        return postDtoList;
    }
    @Override
    public User findById(Long id){
        //UserTable to User
        return mapper.map(
                jpaUserRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("없는 아이디에요.")),
                User.class
        );
    }
    @Override
    public User findByNickname(String nickname){
        return mapper.map(
                jpaUserRepository.findByNickname(nickname).orElseThrow(()-> new IllegalArgumentException("없는 닉네임이에요.")),
                User.class
        );
    }

    @Override
    public List<User> findByIdList(List<Long> idList){
        List<User> users = new ArrayList<>();
        for (Long id : idList) {
            users.add(
                    mapper.map(jpaUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("없는 아이디입니다만?")),User.class
                )
            );
        }
        return users;
    }

    @Override
    public Boolean create(User user) {
        if (jpaUserRepository.existsById(user.getId())){
            throw new IllegalArgumentException("이미 있는 유저입니다만?");
        }
        UserTable userTable = mapper.map(user, UserTable.class);
        jpaUserRepository.save(userTable);
        return jpaUserRepository.existsById(user.getId());
    }


    @Override
    public User update(User user){
        if (!jpaUserRepository.existsById(user.getId())){
            throw new IllegalArgumentException("없는 유저입니다만?");
        }
        UserTable userTable = mapper.map(user, UserTable.class);
        jpaUserRepository.save(userTable);
        return mapper.map(jpaUserRepository.findById(user.getId()), User.class);
    }

    @Override
    public Boolean delete(User user){
        if (!jpaUserRepository.existsById(user.getId())){
            throw new IllegalArgumentException("없는 유저입니다만?");
        }
        UserTable userTable = mapper.map(user, UserTable.class);
        jpaUserRepository.delete(userTable);
        return !jpaUserRepository.existsById(user.getId());
    }

}
