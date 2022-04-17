package app.hdj.datepick.domain.diary.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;

@Getter
public class DiaryFilterParam {

    private String keyword;

    @Positive
    private Long userId;
    @Positive
    private Long placeId;
    @Positive
    private Long courseId;

    public Sort getSort(Sort sort) {
        return sort;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
