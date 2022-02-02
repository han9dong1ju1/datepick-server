package app.hdj.datepick.global.config.security.handler;

import app.hdj.datepick.global.common.BaseResponse;
import app.hdj.datepick.global.error.enums.ErrorCode;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthorizationExceptionHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AuthorizationExceptionHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!response.isCommitted()) {
            response.setStatus(ErrorCode.ACCESS_DENIED.getStatus());
            response.getWriter().print(objectMapper.writeValueAsString(
                    new BaseResponse<>(ErrorCode.ACCESS_DENIED.getMessage(), ErrorCode.ACCESS_DENIED.toString()))
            );
        }
    }

}
