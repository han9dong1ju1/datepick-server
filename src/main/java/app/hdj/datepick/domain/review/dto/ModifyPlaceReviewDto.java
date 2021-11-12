package app.hdj.datepick.domain.review.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModifyPlaceReviewDto {
    private Byte placeOrder;
    private Byte rating;
    private String content;
    private Long placeId;
}

