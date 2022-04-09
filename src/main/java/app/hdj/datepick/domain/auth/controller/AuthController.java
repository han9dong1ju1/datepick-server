package app.hdj.datepick.domain.auth.controller;

import app.hdj.datepick.domain.auth.dto.AuthLoginRequest;
import app.hdj.datepick.domain.auth.dto.AuthTokenResponse;
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

    @PostMapping("login")
    AuthTokenResponse login(@Valid @RequestBody final AuthLoginRequest authLoginRequest) {
        return authService.login(Provider.from(authLoginRequest.getProvider()), authLoginRequest.getToken());
    }

//    @PostMapping("unregister")
//    void unregisterUser(@AuthenticationPrincipal Long userId,
//                        @Valid @RequestBody AuthUnregisterRequest userUnregisterRequest) {
//        authService.unregisterUser(userId, userUnregisterRequest);
//    }

}
