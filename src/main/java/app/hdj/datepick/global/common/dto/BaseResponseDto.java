package app.hdj.datepick.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseDto<T> {

    private String message;
    private String error;
    private T data;

}
