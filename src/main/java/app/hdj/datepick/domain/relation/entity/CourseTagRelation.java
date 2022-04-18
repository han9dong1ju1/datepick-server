package app.hdj.datepick.domain.relation.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.tag.entity.Tag;
import app.hdj.datepick.global.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseTagRelation extends BaseEntity<Long> {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

}
