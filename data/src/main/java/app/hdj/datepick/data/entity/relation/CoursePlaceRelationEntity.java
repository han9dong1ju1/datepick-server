package app.hdj.datepick.data.entity.relation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "course_place_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePlaceRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "place_id", nullable = false)
    private Long placeId;

    @Column(name = "place_order", nullable = false)
    private Byte order;

    @Column(name = "visit_time", nullable = false)
    private Timestamp visitTime;

    @Column(name = "memo", columnDefinition = "varchar(100)", nullable = false)
    private String memo;

}
