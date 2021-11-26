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

    @Column(nullable = false)
    private Byte photoOrder;

    @Column(nullable = false)
    private String photoUrl;

}
