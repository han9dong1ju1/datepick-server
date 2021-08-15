package app.hdj.datepick.advice;

import app.hdj.datepick.controller.UserController;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
@Order(CustomOrder.CONTROLLER_EXCEPTION_ADVICE)
public class UserExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Exception sampleException(RuntimeException e) {
        System.out.println("User Exception Advice");
        return e;
    }

}
