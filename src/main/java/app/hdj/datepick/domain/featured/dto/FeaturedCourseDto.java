package app.hdj.datepick.domain.featured.dto;

import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FeaturedCourseDto {
    private Byte order;
    private CourseMetaDto courseMeta;

    @QueryProjection
    public FeaturedCourseDto(Byte order, CourseMetaDto courseMeta) {
        this.order = order;
        this.courseMeta = courseMeta;
    }
}