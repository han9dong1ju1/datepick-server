package app.hdj.datepick.domain.review.entity;

import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.review.dto.ModifyPlaceReviewDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PlaceReview extends BaseAllTimeEntity<Long> {

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Byte placeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY) //cascade
    @JoinColumn(name = "user_id")
    private User user;

    public void modifyPlaceReview(ModifyPlaceReviewDto modifyPlaceReviewDto){
        this.content = modifyPlaceReviewDto.getContent();
        this.rating = modifyPlaceReviewDto.getRating();
    }
}
