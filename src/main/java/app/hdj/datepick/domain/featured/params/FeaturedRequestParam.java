package app.hdj.datepick.domain.featured.params;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FeaturedRequestParam {

    private Boolean isPinned;
    private Long courseId;

}
