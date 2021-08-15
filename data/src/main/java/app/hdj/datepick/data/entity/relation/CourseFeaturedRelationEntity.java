package app.hdj.datepick.data.entity.relation;


import lombok.*;

import javax.persistence.*;

@Entity(name = "course_featured_relation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseFeaturedRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "featured_id", columnDefinition = "bigint not null")
    private Long featuredId;

    @Column(name = "course_id", columnDefinition = "bigint not null")
    private Long courseId;

    @Column(name = "order", columnDefinition = "tinyint(4) not null")
    private Integer order;
}
