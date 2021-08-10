package app.hdj.datepick.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    private String message;
    private String error;
    private T data;

    public Response(String message, String error, T data) {
        this.message = message;
        this.error = error;
        this.data = data;
    }

}
