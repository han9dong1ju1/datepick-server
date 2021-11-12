package app.hdj.datepick.domain.user.controller;

import app.hdj.datepick.domain.user.dto.UserMetaDto;
import app.hdj.datepick.domain.user.dto.UserModifyDto;
import app.hdj.datepick.domain.user.dto.UserRegisterDto;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.service.UserService;
import app.hdj.datepick.global.config.security.model.TokenUser;
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
    @GetMapping("me")
    User getUserMe(@AuthenticationPrincipal TokenUser tokenUser) {
        return userService.getUser(tokenUser);
    }

    /**
     * 유저 등록
     */
    @PostMapping("register")
    User registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        String provider = userRegisterDto.getProvider();
        String token = userRegisterDto.getToken();
        return userService.registerUser(provider, token);
    }

    /**
     * 유저 정보 수정
     */
    @PatchMapping("{userId}")
    User modifyUser(@AuthenticationPrincipal TokenUser tokenUser,
                    @PathVariable Long userId,
                    @Valid @ModelAttribute UserModifyDto userModifyDto,
                    @RequestParam Boolean removePhoto) {
        return userService.modifyUser(tokenUser, userId, userModifyDto, removePhoto);
    }

    /**
     * 유저 삭제
     */
    @PostMapping("unregister")
    void unregisterUser(@AuthenticationPrincipal TokenUser tokenUser,
                        @Valid @RequestBody UserUnregisterDto userUnregisterRequestDto) {
        userService.unregisterUser(tokenUser, userUnregisterRequestDto);
    }

}
