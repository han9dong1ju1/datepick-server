package app.hdj.datepick.global.error.handler;

import app.hdj.datepick.global.common.BaseResponse;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

// TODO: dev/prod 환경별로 별도 응답 처리
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Request Body, Param Validation 예외 처리
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = ex.getFieldErrors();
        String message = errors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        BaseResponse<Object> response = new BaseResponse<>(
                ErrorCode.INVALID_INPUT_VALUE.getMessage() + " " + message,
                ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, status);
    }

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public BaseResponse<Object> handleCustomException(CustomException e) {
        return new BaseResponse<>(e.getErrorCode());
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleException(Exception e) {
        return new BaseResponse<>(e.getMessage(), e.getClass());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        body = new BaseResponse<>(ex.getMessage(), ex.getClass());
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
