package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.dto.UserRegisterRequest;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    UserPublic getUser(@PathVariable Long userId) {
        return userService.getPublicUser(userId);
    }

    @GetMapping("me")
    User getUserMe(@AuthenticationPrincipal Long userId) {
        return userService.getUser(userId);
    }

    @PatchMapping("me")
    User modifyUser(@AuthenticationPrincipal Long userId,
                    @Valid @RequestBody UserModifyRequest userModifyRequest) {
        return userService.modifyUser(userId, userModifyRequest);
    }

    @PostMapping("me/image")
    void addUserMeImage() {

    }

    @DeleteMapping("users/me/image")
    void removeUserMeImage() {

    }

    @PostMapping("register")
    User registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping("unregister")
    void unregisterUser(@AuthenticationPrincipal Long userId,
                        @Valid @RequestBody UserUnregisterDto userUnregisterRequestDto) {
        userService.unregisterUser(userId, userUnregisterRequestDto);
    }

}
