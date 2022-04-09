package app.hdj.datepick.global.config.security.util;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.config.security.exception.CustomFirebaseAuthException;
import app.hdj.datepick.global.config.security.exception.TokenNoClaimException;
import app.hdj.datepick.global.config.security.model.UserAuthentication;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class FirebaseTokenUtil implements JwtUtil {

    private final FirebaseAuth firebaseAuth;

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            return null;
        }
        FirebaseToken firebaseToken = getFirebaseToken(token);

        // 인증 변수 세팅
        Map<String, Object> claims = firebaseToken.getClaims();

        Long userId = null;
        try {
            userId = Long.valueOf(claims.get("id").toString());
        } catch (NullPointerException e) {
            // claims 에 id/권한 없을 경우 (Firebase 유저 생성 후 users/register api 콜 안함 or 토큰 갱신 안함)
            throw new TokenNoClaimException("id");
        }

        Set<GrantedAuthority> authorities = null;
        try {
            // 토큰에서 권한 가져오기 (Set 활용해서 중복 삭제)
            authorities = ((List<String>) claims.get("authorities")).stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        } catch (NullPointerException e) {
            throw new TokenNoClaimException("authorities");
        }

        return new UserAuthentication(userId, authorities);
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring(7);
    }

    private FirebaseToken getFirebaseToken(String token) {
        try {
            return firebaseAuth.verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            AuthErrorCode errorCode = e.getAuthErrorCode();
            if (errorCode.equals(AuthErrorCode.EXPIRED_ID_TOKEN)) {
                throw new CustomException(ErrorCode.TOKEN_EXPIRED);
            } else if (errorCode.equals(AuthErrorCode.INVALID_ID_TOKEN)) {
                throw new CustomException(ErrorCode.TOKEN_INVALID);
            } else {
                throw new CustomFirebaseAuthException(errorCode);
            }
        }
    }

    @Override
    public String createToken(User user) {
        createAuthUser(user);
        setCustomClaims(user);
        return createCustomToken(user);
    }

    private void createAuthUser(User user) {
        try {
            firebaseAuth.getUser(user.getUid());
        } catch (FirebaseAuthException e) {
            UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
            createRequest.setUid(user.getUid());
            createRequest.setEmail(user.getEmail());
            try {
                firebaseAuth.createUser(createRequest);
            } catch (FirebaseAuthException ex) {
                throw new CustomFirebaseAuthException(ex.getAuthErrorCode());
            }
        }
    }

    private void setCustomClaims(User user) {
        Map<String, Object> claims = Map.of(
                "id", user.getId(),
                "authorities", Set.of("USER"));

        try {
            firebaseAuth.setCustomUserClaims(user.getUid(), claims);
        } catch (FirebaseAuthException e) {
            throw new CustomFirebaseAuthException(e.getAuthErrorCode());
        }
    }

    private String createCustomToken(User user) {
        try {
            return firebaseAuth.createCustomToken(user.getUid());
        } catch (FirebaseAuthException e) {
            throw new CustomFirebaseAuthException(e.getAuthErrorCode());
        }
    }

}
