package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.featured.service.UserService;
import app.hdj.datepick.domain.user.dto.UserCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    /**
     * 모든 유저 목록
     * TODO
     */
    @GetMapping("")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 유저 세부 정보 반환
     * TODO
     */
    @GetMapping("{userId}")
    User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /**
     * 유저 생성
     */
    @PostMapping("")
    User createUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        return null;
    }

    /**
     * 유저 세부 정보 수정
     */
    @PatchMapping("{userId}")
    User updateUser(@PathVariable Long userId, @Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        return null;
    }

    /**
     * 유저 삭제
     * TODO
     */
    @DeleteMapping("{userId}")
    void deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);
    }

}
