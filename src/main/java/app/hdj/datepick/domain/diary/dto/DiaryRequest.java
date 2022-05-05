package app.hdj.datepick.domain.diary.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DiaryRequest {

    @NotNull
    private String content;
    @Positive
    private Float rating;
    @NotNull
    @Positive
    private Long courseId;
    @NotNull
    @Positive
    private Long placeId;
}
