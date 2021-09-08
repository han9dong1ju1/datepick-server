package app.hdj.datepick.domain.review.entity;

import app.hdj.datepick.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "place_review_photo")
public class PlaceReviewPhoto extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "review_id")
    private PlaceReview placeReview;

    @Column(name = "photo_order", nullable = false)
    private Byte photoOrder;

    @Column(name = "photo_url", columnDefinition = "varchar(255)", nullable = false)
    private String photoUrl;

}
