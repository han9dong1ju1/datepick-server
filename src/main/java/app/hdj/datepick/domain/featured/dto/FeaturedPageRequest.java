package app.hdj.datepick.domain.featured.dto;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.Optional;


@Getter
@ToString
public class FeaturedPageRequest {
    private Integer size;
    private Integer page;
    private Sort sort;

    public FeaturedPageRequest(Integer size, Optional<Integer> page, Optional<String> sort) {

        this.size = size;
        this.page = page.orElse(1);
        if (sort.orElse("desc").toLowerCase() == "asc") {
            this.sort = Sort.by("created_at").ascending();
        } else {
            this.sort = Sort.by("created_at").descending();
        }

    }
}
