package app.hdj.datepick.domain.auth.infrastructure;

import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.auth.service.AuthService;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler
    ) {
        Authorize authorize = null;

        if (handler instanceof HandlerMethod) {
            authorize = getAuthorize((HandlerMethod) handler);
        }

        if (authorize != null) {
            String token = AuthorizationExtractor.extract(request);
            validateAuthorization(token, authorize);
        }

        return true;
    }

    private Authorize getAuthorize(HandlerMethod handlerMethod) {
        Authorize authorize = handlerMethod.getMethodAnnotation(Authorize.class);

        if (authorize == null) {
            authorize = handlerMethod.getBean().getClass().getAnnotation(Authorize.class);
        }

        return authorize;
    }

    private void validateAuthorization(String token, Authorize authorize) {
        if (token == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Map<String, Object> payload = jwtUtil.getPayload(token);

        if (!payload.containsKey(AuthService.USER_AUTHORITIES_CLAIM_KEY) || !payload.containsKey(
            AuthService.USER_ID_CLAIM_KEY)
            || !payload.containsKey(AuthService.TOKEN_UUID_CLAIM_KEY)) {
            throw new CustomException(ErrorCode.TOKEN_MALFORMED);
        }

        List<String> authorities = (List<String>) payload.get(AuthService.USER_AUTHORITIES_CLAIM_KEY);

        for (Role role : authorize.value()) {
            if (!authorities.contains(role.toString())) {
                throw new CustomException(ErrorCode.ACCESS_DENIED);
            }
        }
    }
}
