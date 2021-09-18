package app.hdj.datepick.global.config.security.filter.service;

import app.hdj.datepick.global.config.security.model.FirebaseAuthenticationToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public class FirebaseTokenService implements TokenService {

    private String resolveToken(HttpServletRequest request) {
        String token = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }
        return token;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);

        // 토큰이 없을 시 바로 null 반환
        if (token == null) {
            return null;
        }

        // Firebase SDK를 통한 토큰 인증
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseToken firebaseToken = null;
        String firebaseUid = null;

        try {
            firebaseToken = firebaseAuth.verifyIdToken(token);
            firebaseUid = firebaseToken.getUid();
        } catch (FirebaseAuthException e) {
            log.error("Firebase SDK 오류: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("파라미터 오류: {}", e.getMessage());
        }

        if (firebaseUid == null) {
            return null;
        }
        // Firebase UserAuthentication 객체 반환
        log.debug("Firebase Authentication 반환");
        return new FirebaseAuthenticationToken(firebaseUid, firebaseToken);
    }

}
