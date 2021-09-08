package app.hdj.datepick.global.error.exception;

import app.hdj.datepick.global.error.ErrorCode;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
