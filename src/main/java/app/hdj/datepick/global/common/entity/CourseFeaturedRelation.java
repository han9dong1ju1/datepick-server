package app.hdj.datepick.global.common.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.featured.entity.Featured;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "course_featured_relation")
public class CourseFeaturedRelation extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "featured_id",nullable = false)
    private Featured featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @Column(name = "course_order", nullable = false)
    private Byte courseOrder;
}
