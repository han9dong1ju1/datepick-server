package app.hdj.datepick.global.error.exception;

import app.hdj.datepick.global.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = HttpStatus.valueOf(errorCode.getStatus());
        this.code = errorCode.toString();
    }

    public CustomException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

}
