package app.hdj.datepick.domain.auth.controller;

import app.hdj.datepick.domain.auth.dto.LoginRequest;
import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.service.AuthService;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("signin")
    AllTokenResponse signIn(@Valid @RequestBody final LoginRequest authLoginRequest) {
        return null;
    }

//    @PostMapping("unregister")
//    void unregisterUser(@AuthenticationPrincipal Long userId,
//                        @Valid @RequestBody AuthUnregisterRequest userUnregisterRequest) {
//        authService.unregisterUser(userId, userUnregisterRequest);
//    }

}
