package app.hdj.datepick.advice;

import app.hdj.datepick.controller.AuthController;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(CustomOrder.CONTROLLER_EXCEPTION_ADVICE)
@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Exception sampleException(RuntimeException e) {
        System.out.println("Auth Exception Advice");
        return e;
    }

}
