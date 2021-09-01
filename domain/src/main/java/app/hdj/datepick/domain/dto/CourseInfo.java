package app.hdj.datepick.domain.dto;


import app.hdj.datepick.domain.entity.table.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
