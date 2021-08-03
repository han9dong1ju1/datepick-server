package app.hdj.datepick.controller;

import app.hdj.datepick.domain.dto.User;
import app.hdj.datepick.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public List<User> getAllUsers() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return new ArrayList<User>(){{add(user);}};
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable Long userId) {
        if (userId == 13)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fuck");

        return new User(
                userId,
                "sample_user",
                "nickname",
                "profile_url");
    }

    @PostMapping("")
    public User createUser() {
        User user = new User(
                1L,
                "sample_user",
                "nickname",
                "profile_url");

        return user;
    }

    @PatchMapping("{userId}")
    public void updateUser(@PathVariable Long userId) {

    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId) {

    }
}
