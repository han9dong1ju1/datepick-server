package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.course.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseFeaturedInfo {

    private Byte order;
    private Course course;

    @QueryProjection
    public CourseFeaturedInfo(Byte order, Course course) {
        this.order = order;
        this.course = course;
    }
}
