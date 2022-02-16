package app.hdj.datepick.domain.diary.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PlaceReviewDto {


    private Byte placeOrder;
    private Float rating;
    private String content;
    private Long placeId;
//    private List<PhotoUrl> photoUrls;

    @QueryProjection
    public PlaceReviewDto(Byte placeOrder, Float rating, String content, Long placeId) {
        this.placeOrder = placeOrder;
        this.rating = rating;
        this.content = content;
        this.placeId = placeId;
    }

}
