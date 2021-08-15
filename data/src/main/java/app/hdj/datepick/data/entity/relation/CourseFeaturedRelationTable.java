package app.hdj.datepick.data.entity.relation;


import lombok.*;

import javax.persistence.*;

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
