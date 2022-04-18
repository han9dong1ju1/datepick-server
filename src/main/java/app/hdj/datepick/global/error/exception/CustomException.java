package app.hdj.datepick.global.error.exception;

import app.hdj.datepick.global.error.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(errorCode.getStatus());
    }

    public String getCode() {
        return errorCode.toString();
    }

}
