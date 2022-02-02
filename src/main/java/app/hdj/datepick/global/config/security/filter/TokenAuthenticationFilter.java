package app.hdj.datepick.global.config.security.filter;

import app.hdj.datepick.global.config.security.util.TokenUtil;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenUtil tokenUtil;

    public TokenAuthenticationFilter(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        Authentication auth = null;

        try {
            auth = tokenUtil.getAuthentication(request);
        } catch (CustomException e) {
            log.debug("caught auth exception");
            request.setAttribute("TokenException", e);
        }

        return auth;
    }

}
