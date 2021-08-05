package app.hdj.datepick.controller;

import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<User> getAllUsers() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return new ArrayList<User>(){{add(user);}};
    }

    @GetMapping("me")
    User getMyUser() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @GetMapping("{userId}")
    User getUser(@PathVariable Long userId) {
        if (userId == 13)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fuck");

        return new User(
                userId,
                "sample_user",
                "nickname",
                "profile_url");
    }

    @PostMapping("")
    User createUser() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @PostMapping("me")
    User createMyUser() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @PatchMapping("me")
    User updateMyUser() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @PatchMapping("{userId}")
    User updateUser(@PathVariable Long userId) {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @DeleteMapping("{userId}")
    void deleteUser(@PathVariable Long userId) {

    }
}
