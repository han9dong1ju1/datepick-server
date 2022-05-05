package app.hdj.datepick.global.common;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

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

    public static <T> CustomPage<T> from(Page<T> page) {
        return new CustomPage<>(page.getTotalElements(), page.getTotalPages(), page.getNumber(),
                                page.getContent());
    }
}