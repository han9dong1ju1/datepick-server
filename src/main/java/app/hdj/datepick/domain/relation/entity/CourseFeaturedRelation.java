package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseFeaturedRelation extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "featured_id", nullable = false)
    private Featured featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private Byte courseOrder;
}
