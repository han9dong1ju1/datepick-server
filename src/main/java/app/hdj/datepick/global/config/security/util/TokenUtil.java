package app.hdj.datepick.global.config.security.util;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenUtil {

    Authentication getAuthentication(HttpServletRequest request);

}
