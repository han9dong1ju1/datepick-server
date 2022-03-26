package app.hdj.datepick.domain.diary.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class DiaryModifyRequestDto {
    ModifyDiaryDto diary;
    List<ModifyPlaceReviewDto> placeReviews;
}
