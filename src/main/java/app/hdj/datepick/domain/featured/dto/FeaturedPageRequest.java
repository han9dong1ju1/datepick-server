package app.hdj.datepick.domain.featured.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@Getter
@ToString
public class FeaturedPageRequest {

    @NotNull
    private Integer size;
    private Integer page;
    private Sort sort;

    public FeaturedPageRequest(Integer size, Optional<Integer> page, Optional<String> sort) {

        this.size = size;
        this.page = page.orElse(0);
        if (sort.orElse("desc").toLowerCase() == "asc") {
            this.sort = Sort.by("created_at").ascending();
        } else {
            this.sort = Sort.by("created_at").descending();
        }

    }
}
