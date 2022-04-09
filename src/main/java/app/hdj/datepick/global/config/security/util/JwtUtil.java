package app.hdj.datepick.global.config.security.util;

import app.hdj.datepick.domain.user.entity.User;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtUtil {
    Authentication getAuthentication(HttpServletRequest request);
    String createToken(User user);
}
