package app.hdj.datepick.global.config.security.handler;

import app.hdj.datepick.global.common.BaseResponse;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public AuthenticationExceptionHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (!response.isCommitted()) {
            Object attr = request.getAttribute("TokenException");
            response.setStatus(ErrorCode.UNAUTHORIZED.getStatus());
            if (attr != null) {
                CustomException tokenException = ((CustomException) attr);
                response.getWriter().print(objectMapper.writeValueAsString(
                        new BaseResponse<>(tokenException.getMessage(), tokenException.getCode()))
                );
            }
            else {
                response.getWriter().print(objectMapper.writeValueAsString(
                        new BaseResponse<>(ErrorCode.UNAUTHORIZED.getMessage(), ErrorCode.UNAUTHORIZED.toString()))
                );
            }
        }
    }

}
