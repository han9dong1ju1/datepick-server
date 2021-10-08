package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.UserMetaDto;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.dto.UserModifyDto;
import app.hdj.datepick.domain.user.dto.UserRegisterDto;
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
     */
    @GetMapping("{userId}")
    UserMetaDto getUser(@PathVariable Long userId) {
        return userService.getUserPublic(userId);
    }

    /**
     * 내 유저 정보 반환
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @GetMapping("me")
    User getUserMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getUser(userDetails.getId());
    }

    /**
     * 유저 등록
     */
    @PostMapping("register")
    void registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        String provider = userRegisterDto.getProvider();
        String token = userRegisterDto.getToken();
        userService.registerUser(provider, token);
    }

    /**
     * 유저 정보 수정
     */
    @PreAuthorize("isAuthenticated() and (hasAnyAuthority('USER') and #userDetails.id == #userId)")
    @PatchMapping("{userId}")
    User modifyUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                    @PathVariable Long userId,
                    @Valid @RequestBody UserModifyDto userModifyDto) {
        return userService.modifyUser(userId, userModifyDto);
    }

    /**
     * 유저 삭제
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PostMapping("unregister")
    void unregisterUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                        @Valid @RequestBody UserUnregisterDto userUnregisterRequestDto) {
        userService.unregisterUser(userDetails.getId(), userDetails.getUid(), userUnregisterRequestDto);
    }

}
