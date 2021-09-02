package app.hdj.datepick.domain.course.dto;


import app.hdj.datepick.domain.course.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseInfo {
    private Byte order;
    private Course course;

    @QueryProjection
    public CourseInfo(Byte order, Course course) {
        this.order = order;
        this.course = course;
    }
}
