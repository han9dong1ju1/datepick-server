package app.hdj.datepick.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomPage<T> {

    private long totalCount;
    private long currentPage;
    private boolean last;
    private T content;

}