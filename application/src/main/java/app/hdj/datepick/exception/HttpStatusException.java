package app.hdj.datepick.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpStatusException extends Exception {

    private HttpStatus status;

    public HttpStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
