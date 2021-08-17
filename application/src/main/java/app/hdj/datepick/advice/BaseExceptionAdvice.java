package app.hdj.datepick.advice;

import app.hdj.datepick.dto.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(CustomOrder.BASE_EXCEPTION_ADVICE)
public class BaseExceptionAdvice {

    // Default Exception Handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Exception defaultException(Exception e) {
        log.debug("Default Exception Handler - {}", e.getClass().getName());
        return e;
    }

    // Request Body Validation Check
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponseDto<List<ObjectError>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new BaseResponseDto<>(
                "Validation failed for object='" + e.getObjectName() + "'. Error count: " + e.getErrorCount(),
                e.getClass().getSimpleName(),
                e.getAllErrors());
    }

}
