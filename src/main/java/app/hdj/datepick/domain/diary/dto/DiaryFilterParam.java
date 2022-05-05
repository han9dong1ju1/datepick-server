package app.hdj.datepick.domain.diary.dto;

import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryFilterParam {

    private String keyword;
    @Positive
    private Long userId;
    @Positive
    private Long placeId;
    @Positive
    private Long courseId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
