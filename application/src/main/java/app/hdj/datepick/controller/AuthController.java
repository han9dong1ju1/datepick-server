package app.hdj.datepick.controller;

import app.hdj.datepick.domain.model.User;
import app.hdj.datepick.dto.auth.AuthRegisterRequestDto;
import app.hdj.datepick.dto.auth.AuthRegisterResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Firebase Auth 관련 처리
 */
@RestController
@RequestMapping("v1/auth")
public class AuthController {

    /**
     * 로그인
     * TODO
     */
    @GetMapping("login")
    public User login() {
        return new User();
    }

    /**
     * Firebase Auth 계정 생성 or 확인 -> Custom Token 반환
     * TODO
     */
    @PostMapping("register")
    public AuthRegisterResponseDto register(
            @RequestParam("source") String source,
            @Valid @RequestBody AuthRegisterRequestDto registerRequestDto
            ) {
        // -> profileInfo(source: Naver or Kakao)

        // -> Create Firebase Auth User
        // CreateRequest request = new CreateRequest()
        //    .setUid("some-uid")
        //    .setEmail("user@example.com")
        //    .setPhoneNumber("+11234567890");
        // UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // -> Create Custom Token
        // firebaseUid = userRecord.getUid();
        // String customToken = FirebaseAuth.getInstance().createCustomToken(uid);

        // -> Map Firebase CustomToken to FirebaseCustomTokenDto

        return new AuthRegisterResponseDto();
    }

    /**
     * 로그아웃
     * TODO
     */
    @GetMapping("logout")
    public void logout() {
        throw new RuntimeException("Auth exception");
    }

    /**
     * 회원 탈퇴 (Firebase Auth 탈퇴)
     * TODO
     */
    @PostMapping("unregister")
    public void unregister() {

    }
}
