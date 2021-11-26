package app.hdj.datepick.domain.review.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import java.util.List;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
