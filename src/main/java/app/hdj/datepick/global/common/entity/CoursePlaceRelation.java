package app.hdj.datepick.global.common.entity;


import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.place.entity.Place;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "course_place_relation")
public class CoursePlaceRelation extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false) //참조 테이블의 기본키 맵핑
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "place_order", nullable = false)
    private Byte placeOrder;

    @Column(name = "visit_time", nullable = false)
    private LocalDateTime visitTime;

    @Column(name = "memo", columnDefinition = "varchar(100)", nullable = false)
    private String memo;

}
