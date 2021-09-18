package app.hdj.datepick.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import java.util.List;


public class PlaceReviewDto {

    private Byte inDiaryOrder;
    private Byte rating;
    private String content;
    private List<PhotoUrl> photoUrls;

    public class PhotoUrl{
        private Byte photoOrder;
        private String photoUrl;

        @QueryProjection
        public PhotoUrl(Byte photoOrder, String photoUrl) {
            this.photoOrder = photoOrder;
            this.photoUrl = photoUrl;
        }
    }

    @QueryProjection
    public PlaceReviewDto(Byte inDiaryOrder, Byte rating, String content) {
        this.inDiaryOrder = inDiaryOrder;
        this.rating = rating;
        this.content = content;
    }

}
