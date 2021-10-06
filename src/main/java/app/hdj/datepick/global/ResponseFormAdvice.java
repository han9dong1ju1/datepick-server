package app.hdj.datepick.global;

import app.hdj.datepick.global.common.dto.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@Slf4j
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
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = null;
        String error = null;
        Object data = null;

        // Custom exception 처리
        if (body instanceof Exception) {
            message = ((Exception) body).getMessage();
            error = ((Exception) body).getClass().getSimpleName();
        }
        else if (body instanceof BaseResponseDto) {
            BaseResponseDto bodyDto = (BaseResponseDto)body;
            message = bodyDto.getMessage();
            error = bodyDto.getError();
            data = bodyDto.getData();
        }
        else if (body instanceof Map) {
            Map bodyMap = (Map)body;
            if (bodyMap.containsKey("error")) {
                error = bodyMap.get("error").toString();
                bodyMap.remove("error");
            }
            if (bodyMap.containsKey("message")) {
                message = bodyMap.get("message").toString();
                bodyMap.remove("message");
            }
            if (bodyMap.containsKey("status")) {
                httpStatus = HttpStatus.valueOf((int)bodyMap.get("status"));
                bodyMap.remove("status");
            }
            data = body;
        } else {
            httpStatus = HttpStatus.OK;
            data = body;
        }

        response.setStatusCode(httpStatus);
        return new BaseResponseDto<>(message, error, data);
    }

}
