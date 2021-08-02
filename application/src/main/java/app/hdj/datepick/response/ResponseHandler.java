package app.hdj.datepick.response;

import app.hdj.datepick.exception.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    private static <T> ResponseEntity<Response<T>> createResponse(String message, HttpStatus status, T data) {
        Response<T> response = new Response<T>(message, status.value(), data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Response<T>> createSuccessResponse(T data) {
        return ResponseHandler.createResponse("Success", HttpStatus.OK, data);
    }

    public static <T> ResponseEntity<Response<T>> createErrorResponse(HttpStatusException exception) {
        return ResponseHandler.createResponse(
                exception.getMessage(),
                exception.getStatus(),
                null
        );
    }

}
