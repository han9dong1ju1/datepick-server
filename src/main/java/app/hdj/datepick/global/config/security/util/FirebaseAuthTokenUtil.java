package app.hdj.datepick.global.config.security.util;

import app.hdj.datepick.global.config.security.model.FirebaseAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class FirebaseAuthTokenUtil implements TokenUtil {

    public Authentication getAuthentication(HttpServletRequest request) {
        // Token 파싱
        String token = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        // Firebase UserAuthentication 객체 반환
        if (token == null) {
            return null;
        }
        log.debug("Firebase Authentication 반환");
        return new FirebaseAuthToken(token);
    }

}
