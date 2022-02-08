package app.hdj.datepick.global.common;

import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.enums.Sort;
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

    @ValueOfEnum(enumClass = Sort.class, acceptedValues = {"latest"})
    private String sort;

}
