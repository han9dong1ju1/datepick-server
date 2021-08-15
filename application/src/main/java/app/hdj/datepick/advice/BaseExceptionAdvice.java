package app.hdj.datepick.advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(CustomOrder.BASE_EXCEPTION_ADVICE)
public class BaseExceptionAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object defaultException(Exception e) {
        // 기본 예외 처리 TODO
        System.out.println("Base Exception Advice");
        return e;
    }

}
