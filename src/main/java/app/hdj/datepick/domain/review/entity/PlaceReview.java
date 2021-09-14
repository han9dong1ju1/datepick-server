package app.hdj.datepick.domain.review.entity;


import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "place_review")
public class PlaceReview extends BaseTimeEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Column(name = "review_order", nullable = false)
    private Byte reviewOrder;

    @Column(name = "rating", nullable = false)
    private Byte rating;

    @Column(name = "content", nullable = false)
    @ColumnDefault("''")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY) //cascade
    @JoinColumn(name = "user_id")
    private User user;

}
