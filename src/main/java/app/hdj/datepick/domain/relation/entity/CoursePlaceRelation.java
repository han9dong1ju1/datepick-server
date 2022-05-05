package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.place.entity.Place;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CoursePlaceRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Byte placeOrder;

    @Column
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false) //참조 테이블의 기본키 맵핑
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Builder
    private CoursePlaceRelation(Long id, Byte placeOrder, String memo, Course course, Place place, Diary diary) {
        this.id = id;
        this.placeOrder = placeOrder;
        this.memo = memo;
        this.course = course;
        this.place = place;
        this.diary = diary;
    }
}
