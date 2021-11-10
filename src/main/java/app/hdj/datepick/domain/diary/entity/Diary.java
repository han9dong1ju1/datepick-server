package app.hdj.datepick.domain.diary.entity;


import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.enums.Style;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "diary")
public class Diary extends BaseTimeEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "like_count", nullable = false)
    @ColumnDefault("0")
    private Integer likeCount;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PlaceReview> placeReviews;

}
