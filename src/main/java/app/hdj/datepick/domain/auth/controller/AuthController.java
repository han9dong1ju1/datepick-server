package app.hdj.datepick.domain.auth.controller;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.AuthUuid;
import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.dto.RefreshTokenRequest;
import app.hdj.datepick.domain.auth.service.AuthService;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("signin/{provider}")
    AllTokenResponse signInByOAuth(@Valid @PathVariable @ValueOfEnum(enumClass = Provider.class) String provider,
                                   @RequestParam String code) {
        return authService.signInByOAuth(Provider.from(provider), code);
    }

    @PostMapping("refresh")
    AllTokenResponse refreshToken(@RequestBody final RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest.getRefreshToken());
    }

    @GetMapping("signout")
    void signOut(@AuthPrincipal Long userId, @AuthUuid String uuid) {
        authService.signOut(userId, uuid);
    }

}