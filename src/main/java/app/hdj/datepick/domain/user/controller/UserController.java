package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.common.ImageUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
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
    User getUserMe(@AuthPrincipal Long userId) {
        return userService.getUser(userId);
    }

    @PatchMapping("me")
    User modifyUser(@AuthPrincipal Long userId,
                    @Valid @RequestBody UserModifyRequest userModifyRequest) {
        return userService.modifyUser(userId, userModifyRequest);
    }

    @PostMapping("me/image")
    ImageUrl addUserMeImage(
            @AuthPrincipal Long userId,
            @NotNull @ImageFile @ModelAttribute MultipartFile image) {
        return userService.addUserImage(userId, image);
    }

    @DeleteMapping("me/image")
    void removeUserMeImage(@AuthPrincipal Long userId) {
        userService.removeUserImage(userId);
    }

}
