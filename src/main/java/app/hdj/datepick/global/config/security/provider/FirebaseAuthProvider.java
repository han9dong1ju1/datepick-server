package app.hdj.datepick.global.config.security.provider;

import app.hdj.datepick.global.config.security.model.CustomUserDetails;
import app.hdj.datepick.global.config.security.model.FirebaseAuthToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class FirebaseAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getCredentials();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseToken firebaseToken = null;

        // Firebase SDK를 통한 토큰 검증
        try {
            firebaseToken = firebaseAuth.verifyIdToken(token);
            log.debug("TenantId: {}", firebaseToken.getTenantId());
            log.debug("Picture: {}", firebaseToken.getPicture());
            log.debug("Issuer: {}", firebaseToken.getIssuer());
            log.debug("Claims: {}", firebaseToken.getClaims().toString());
        } catch (FirebaseAuthException e) {
            log.error("Firebase SDK 오류: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("파라미터 오류: {}", e.getMessage());
        }
        if (firebaseToken == null) {
            log.debug("토큰 없음");
            return null;
        }

        // 인증 변수 세팅
        Long id = null;
        String uid = firebaseToken.getUid();
        Map<String, Object> claims = firebaseToken.getClaims();
        try {
            id = Long.valueOf(claims.get("id").toString());
        } catch (NumberFormatException e) {
            log.error("id parsing 오류: {}", e.getMessage());
            return null;
        } catch (NullPointerException e) {
            // getClaims에 id가 없을 경우 오류 (Firebase 유저 생성 후 users/register api 콜 안한 것)
            log.error("Firebase claim에 id 없음: {}", e.getMessage());
        }

        // CustomUserDetails 객체 생성
        CustomUserDetails user = new CustomUserDetails(id, firebaseToken.getUid());


        // 토큰에서 권한 가져오기


        // 위 정보로 새로운 CustomUserAuthToken 객체 생성 후 반환
        return new FirebaseAuthToken(user, token, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthToken.class.isAssignableFrom(authentication));
    }

}