package app.hdj.datepick.data.repository;

import app.hdj.datepick.data.entity.UserTable;
import app.hdj.datepick.data.query.JpaUserRepository;
import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


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
        List<User> users = mapper.map(userTables, listType);

        return users;
    }

    @Override
    public Page<User> findAll(Pageable pageable){
        //https://stackoverflow.com/questions/30644543/convert-pageentity-to-pagedtoentitydto 참고
        Page<UserTable> userTables = jpaUserRepository.findAll(pageable);
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> users = mapper.map(userTables.getContent(), listType);
        return new PageImpl<>(users, pageable, userTables.getTotalElements());
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
        List<User> users = new ArrayList<>();
        users = idList.stream()
                .map(id -> findById(id))
                .map(userTable -> mapper.map(userTable, User.class))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public Boolean create(User user) {
        try{
            if (jpaUserRepository.existsById(user.getId())) {
                throw new IllegalAccessException(String.format("해당 id : %d의 유저는 이미 존재합니다.", user.getId()));
            }
        }catch (IllegalAccessException e) {
            //Exception handler
            return false;
        }
        UserTable userTable = mapper.map(user, UserTable.class);
        jpaUserRepository.save(userTable);
        return jpaUserRepository.existsById(user.getId());
    }


    @Override
    public User update(User user){
        UserTable userTable = jpaUserRepository.findById(user.getId()).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d의 유저가 존재하지 않습니다", user.getId())));
        mapper.map(user, userTable);
        jpaUserRepository.save(userTable);
        return mapper.map(userTable, User.class);
    }

    @Override
    public Boolean delete(User user){
        UserTable userTable = jpaUserRepository.findById(user.getId()).orElseThrow(()-> new NoSuchElementException(String.format("해당 id : %d의 유저가 존재하지 않습니다", user.getId())));
        jpaUserRepository.delete(userTable);
        return !jpaUserRepository.existsById(user.getId());
    }

}
