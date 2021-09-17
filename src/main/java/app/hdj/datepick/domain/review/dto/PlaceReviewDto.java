package app.hdj.datepick.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;


public class PlaceReviewDto {

    private Byte inDiaryOrder;
    private Byte rating;
    private String content;
    private PhotoUrls photoUrls;

    class PhotoUrls{
        private Byte photoOrder;
        private String photoUrl;

        @QueryProjection
        public PhotoUrls(Byte photoOrder, String photoUrl) {
            this.photoOrder = photoOrder;
            this.photoUrl = photoUrl;
        }
    }
}
