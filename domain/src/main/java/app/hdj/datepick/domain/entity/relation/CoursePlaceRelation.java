package app.hdj.datepick.domain.entity.relation;


import app.hdj.datepick.domain.entity.BaseEntity;
import app.hdj.datepick.domain.entity.table.Course;
import app.hdj.datepick.domain.entity.table.Place;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private Byte order;

    @Column(name = "visit_time", nullable = false)
    private LocalDateTime visitTime;

    @Column(name = "memo", columnDefinition = "varchar(100)", nullable = false)
    private String memo;

}
