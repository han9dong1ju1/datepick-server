package app.hdj.datepick.domain.review.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModifyPlaceReviewDto {
    //place Order 만 지정하면 place Id 는 알아서 지정해주도록.
    private Byte placeOrder;
    private Float rating;
    private String content;
}

