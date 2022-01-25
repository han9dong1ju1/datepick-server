package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CoursePlaceRelation extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false) //참조 테이블의 기본키 맵핑
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    private Byte placeOrder;

    @Column
    private Time visitTime;

    @Column
    private String memo;

}
