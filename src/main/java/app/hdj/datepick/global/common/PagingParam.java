package app.hdj.datepick.global.common;

import app.hdj.datepick.global.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingParam {

    private Long page;
    private Long size;
    private Sort sort;

}
