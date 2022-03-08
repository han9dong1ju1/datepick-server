package app.hdj.datepick.domain.diary.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class DiaryDetailDto {

    private Long id;
    private Long courseId;
    private Long userId;
    private String title;
    private Long likeCount;
    private String style;
    private List<PlaceReviewDto> placeReviews;

    @QueryProjection
    public DiaryDetailDto(Long id, Long courseId, Long userId, String title, Long likeCount, String style, List<PlaceReviewDto> placeReviews) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.title = title;
        this.likeCount = likeCount;
        this.style = style;
        this.placeReviews = placeReviews;
    }
}
