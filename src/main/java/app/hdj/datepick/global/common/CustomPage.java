package app.hdj.datepick.global.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class CustomPage<T> {

    private final long totalCount;
    private final int totalPageCount;
    private final int currentPage;
    private final boolean last;
    private final List<T> content;

    public CustomPage(long totalCount, int totalPageCount, int currentPage, List<T> content) {
        this.totalCount = totalCount;
        this.totalPageCount = totalPageCount;
        this.currentPage = currentPage;
        this.last = totalPageCount == (currentPage + 1);
        this.content = content;
    }
}