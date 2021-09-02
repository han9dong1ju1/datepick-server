package app.hdj.datepick.global;

import app.hdj.datepick.global.common.dto.BaseResponseDto;
import app.hdj.datepick.global.config.CustomOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@Order(CustomOrder.FINAL)
@RestControllerAdvice
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
        if (body instanceof Exception) {
            // TODO: request body logging
            return new BaseResponseDto<Object>(
                    ((Exception) body).getMessage(),
                    ((Exception) body).getClass().getSimpleName(),
                    null);
        }
        else if (body instanceof BaseResponseDto) {
            return body;
        }
        return new BaseResponseDto<Object>(null, null, body);
    }

}
