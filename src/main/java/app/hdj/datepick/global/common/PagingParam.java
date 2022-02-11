package app.hdj.datepick.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@AllArgsConstructor
public class PagingParam {

    @NotNull
    @PositiveOrZero
    private Integer page;

    @NotNull
    @Positive
    private Integer size;

}
