package app.hdj.datepick.data.entity.relation;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "course_place_relation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursePlaceRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_id", columnDefinition = "bigint not null")
    private Long courseId;

    @Column(name = "place_id", columnDefinition = "bigint not null")
    private Long placeId;

    @Column(name = "order", columnDefinition = "tinyint(4) not null")
    private Integer order;

    @Column(name = "visit_time", columnDefinition = "Timestamp not null")
    private Timestamp visitTime;

}
