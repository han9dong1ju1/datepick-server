package app.hdj.datepick.advice;

import app.hdj.datepick.dto.BaseResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Order(CustomOrder.FINAL)
public class ResponseFormAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        /**
         * 형식 변경 처리
         */
        if (body instanceof Exception) {
            return new BaseResponseDto<Object>(
                    ((Exception) body).getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    body);
        }
        return new BaseResponseDto<Object>(null, null, body);
    }

}
