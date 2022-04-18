package app.hdj.datepick.domain.auth.controller;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.AuthUuid;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.auth.dto.AllTokenResponse;
import app.hdj.datepick.domain.auth.dto.RefreshTokenRequest;
import app.hdj.datepick.domain.auth.service.AuthService;
import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.domain.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("signin/{provider}")
    AllTokenResponse signInByOAuth(@PathVariable String provider,
                                   @RequestParam String code) {
        return authService.signInByOAuth(Provider.from(provider), code);
    }

    @PostMapping("refresh")
    AllTokenResponse refreshToken(@RequestBody final RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest.getRefreshToken());
    }

    @Authorize(Role.USER)
    @GetMapping("signout")
    void signOut(@AuthPrincipal Long userId, @AuthUuid String uuid) {
        authService.signOut(userId, uuid);
    }

}