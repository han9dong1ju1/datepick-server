package app.hdj.datepick.domain.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


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
