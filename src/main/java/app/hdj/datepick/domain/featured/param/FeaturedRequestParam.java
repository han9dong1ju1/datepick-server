package app.hdj.datepick.domain.featured.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class FeaturedRequestParam {

    private Boolean isPinned;

    @Positive
    private Long courseId;

}
