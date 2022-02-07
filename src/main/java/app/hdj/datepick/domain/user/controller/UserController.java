package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.dto.UserRegisterRequest;
import app.hdj.datepick.domain.user.dto.UserUnregisterRequest;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.common.ImageUploadRequest;
import app.hdj.datepick.global.common.ImageUrl;
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
    ImageUrl addUserMeImage(
            @AuthenticationPrincipal Long userId,
            @Valid @ModelAttribute ImageUploadRequest image) {
        return userService.addUserImage(userId, image.getImage());
    }

    @DeleteMapping("me/image")
    void removeUserMeImage(@AuthenticationPrincipal Long userId) {
        userService.removeUserImage(userId);
    }

    @PostMapping("register")
    User registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping("unregister")
    void unregisterUser(@AuthenticationPrincipal Long userId,
                        @Valid @RequestBody UserUnregisterRequest userUnregisterRequest) {
        userService.unregisterUser(userId, userUnregisterRequest);
    }

}
