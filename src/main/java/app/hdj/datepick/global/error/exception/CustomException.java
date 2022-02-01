package app.hdj.datepick.global.error.exception;

import app.hdj.datepick.global.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = HttpStatus.valueOf(errorCode.getStatus());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.httpStatus = HttpStatus.valueOf(errorCode.getStatus());
        this.errorCode = errorCode;
    }

}
