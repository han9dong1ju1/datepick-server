package app.hdj.datepick.global.common;

import app.hdj.datepick.global.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.validation.BindException;

@Getter
public class BaseResponse<T> {

    private final String message;
    private final String error;
    private final T data;

    public BaseResponse(String message, String error, T data) {
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public BaseResponse(String message, String error) {
        this.message = message;
        this.error = error;
        this.data = null;
    }

    // 커스텀 예외 응답 생성
    public BaseResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.error = errorCode.toString();
        this.data = null;
    }

    // 커스텀 예외 응답 새로운 메시지로 생성
    public BaseResponse(String message, ErrorCode errorCode) {
        this.message = message;
        this.error = errorCode.toString();
        this.data = null;
    }

    // 일반 예외 응답 생성
    public BaseResponse(String message, Class<? extends Exception> c) {
        this.message = message;
        this.error = c.getSimpleName();
        this.data = null;
    }

}