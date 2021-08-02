package app.hdj.datepick.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    private String message;
    private int code;
    private T data;

    public Response(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
