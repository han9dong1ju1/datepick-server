package app.hdj.datepick.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private String message;
    private String error;
    private T data;

}