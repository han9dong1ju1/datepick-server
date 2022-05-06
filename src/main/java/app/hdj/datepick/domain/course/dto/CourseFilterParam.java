package app.hdj.datepick.domain.course.dto;

import java.util.List;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseFilterParam {

    private String keyword;
    private List<@Positive Integer> tagId;
    @Positive
    private Long featuredId;
    @Positive
    private Long placeId;
    @Positive
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
