package app.hdj.datepick.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class CourseFilterParam {

    private String keyword;

    private List<@Positive Byte> tagId;

    @Positive
    private Long featuredId;

    @Positive
    private Long placeId;

    @Positive
    private Long userId;

    public Sort getSort(Sort sort) {
        if (featuredId != null) {
            return Sort.unsorted();
        }
        return sort;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
