package app.hdj.datepick.global.config.aop;

import app.hdj.datepick.global.common.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "app.hdj.datepick.domain")
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
        MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (body == null) {
            return new BaseResponse<>(null, null, null);
        } else if (returnType.getParameterType().equals(BaseResponse.class)
            || returnType.getParameterType().equals(ResponseEntity.class)) {
            return body;
        }
        return new BaseResponse<>(null, null, body);
    }
}
