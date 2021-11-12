package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.domain.review.dto.ModifyPlaceReviewDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DiaryModifyRequestDto {
    ModifyDiaryDto diary;
    List<ModifyPlaceReviewDto> placeReviews;
}
