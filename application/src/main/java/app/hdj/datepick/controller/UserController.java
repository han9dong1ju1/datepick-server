package app.hdj.datepick.controller;

import app.hdj.datepick.domain.model.User;
import app.hdj.datepick.dto.user.UserCreateRequestDto;
import app.hdj.datepick.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 모든 유저 목록
     * TODO
     */
    @GetMapping("")
    List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * 유저 세부 정보 반환
     * TODO
     */
    @GetMapping("{userId}")
    User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    /**
     * 유저 생성
     */
    @PostMapping("")
    User createUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        User user = userCreateRequestDto.createUser();
        return userService.create(user);
    }

    /**
     * 유저 세부 정보 수정
     */
    @PatchMapping("{userId}")
    User updateUser(@PathVariable Long userId, @Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        User user = userCreateRequestDto.createUser();
        return userService.update(user);
    }

    /**
     * 유저 삭제
     * TODO
     */
    @DeleteMapping("{userId}")
    void deleteUser(@PathVariable Long userId) {
        throw new RuntimeException("user delete error");
        //userService.delete(new User());
    }
}
