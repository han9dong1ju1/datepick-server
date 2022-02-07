package app.hdj.datepick.domain.featured.dto;
import lombok.*;
import org.springframework.data.domain.Sort;


@Getter
public class FeaturedPageRequest {
    private int size;
    private int page;
    private Sort sort;

    public FeaturedPageRequest(int size, int page, String sort) {
        this.size = size;
        this.page = page;
        if (sort.toLowerCase() == "asc") {
            this.sort = Sort.by("created_at").ascending();
        }
        else {
            this.sort = Sort.by("created_at").descending();
        }

    }
}
