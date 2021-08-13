package app.hdj.datepick.data.entity.relation;


import app.hdj.datepick.data.entity.FeaturedTable;
import app.hdj.datepick.domain.dto.Course;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "course_featured_relation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseFeaturedRelationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "featured_id", columnDefinition = "int unsigned not null")
    private Long featured_id;

    @Column(name = "course_id", columnDefinition = "int unsigned not null")
    private Long course_id;

    @Column(name = "order", columnDefinition = "tinyint(4) not null")
    private String order;
}
