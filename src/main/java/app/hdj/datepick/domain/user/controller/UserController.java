package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.request.UserUnregisterRequestDto;
import app.hdj.datepick.domain.user.dto.request.UserUpdateRequestDto;
import app.hdj.datepick.domain.user.dto.request.UserRegisterRequestDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.config.security.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    /**
     * 유저 프로필 정보 반환
     * TODO: 반환 값 meta로 수정
     */
    @GetMapping("{userId}")
    User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /**
     * 내 유저 정보 반환
     */
    @PreAuthorize("isAuthenticated()")      // TODO: 권한 수정
    @GetMapping("me")
    User getUserMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getUser(userDetails.getId());
    }

    /**
     * 유저 등록
     */
    @PostMapping("register")
    void registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        String provider = userRegisterRequestDto.getProvider();
        String token = userRegisterRequestDto.getToken();
        userService.registerUser(provider, token);
    }

    /**
     * 유저 정보 수정
     */
    @PreAuthorize("isAuthenticated()")      // TODO: 권한 수정
    @PatchMapping("{userId}")
    User updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    /**
     * 유저 삭제
     */
    @PreAuthorize("isAuthenticated()")      // TODO: 권한 수정
    @PostMapping("unregister")
    void unregisterUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                        @Valid @RequestBody UserUnregisterRequestDto userUnregisterRequestDto) {
        userService.unregisterUser(userDetails.getId(), userDetails.getUid(), userUnregisterRequestDto);
    }

}
