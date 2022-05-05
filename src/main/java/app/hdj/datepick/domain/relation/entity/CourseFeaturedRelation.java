package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.featured.entity.Featured;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseFeaturedRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Byte courseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "featured_id", nullable = false)
    private Featured featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Builder
    private CourseFeaturedRelation(Long id, Byte courseOrder, Featured featured, Course course) {
        this.id = id;
        this.courseOrder = courseOrder;
        this.featured = featured;
        this.course = course;
    }
}
