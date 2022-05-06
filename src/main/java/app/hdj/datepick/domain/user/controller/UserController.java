package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.user.dto.UserRequest;
import app.hdj.datepick.domain.user.dto.UserResponse;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.common.ImageUrlResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    UserResponse getUser(@PathVariable Long userId) {
        return userService.getPublicUser(userId);
    }

    @Authorize({Role.USER})
    @GetMapping("me")
    User getUserMe(@AuthPrincipal Long userId) {
        return userService.getUser(userId);
    }

    @Authorize({Role.USER})
    @PatchMapping("me")
    User modifyUser(
        @AuthPrincipal Long userId, @Valid @RequestBody UserRequest userRequest
    ) {
        return userService.modifyUser(userId, userRequest);
    }

    @Authorize({Role.USER})
    @PostMapping("me/image")
    ImageUrlResponse addUserMeImage(
        @AuthPrincipal Long userId, @NotNull @ImageFile @ModelAttribute MultipartFile image
    ) {
        return userService.addUserImage(userId, image);
    }

    @Authorize({Role.USER})
    @DeleteMapping("me/image")
    void removeUserMeImage(@AuthPrincipal Long userId) {
        userService.removeUserImage(userId);
    }
}
