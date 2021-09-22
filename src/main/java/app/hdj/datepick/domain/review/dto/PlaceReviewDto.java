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

    private Byte inDiaryOrder;
    private Byte rating;
    private String content;
//    private List<PhotoUrl> photoUrls;

    @QueryProjection
    public PlaceReviewDto(Byte inDiaryOrder, Byte rating, String content) {
        this.inDiaryOrder = inDiaryOrder;
        this.rating = rating;
        this.content = content;
    }

}
