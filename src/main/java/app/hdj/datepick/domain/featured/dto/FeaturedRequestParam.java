package app.hdj.datepick.domain.featured.dto;

import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeaturedRequestParam {

    private Boolean isPinned;
    @Positive
    private Long courseId;
}
