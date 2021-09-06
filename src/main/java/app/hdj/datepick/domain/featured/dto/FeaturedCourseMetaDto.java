package app.hdj.datepick.domain.featured.dto;

import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FeaturedCourseMetaDto {
    private Byte order;
    private CourseMetaDto courseMeta;

    @QueryProjection
    public FeaturedCourseMetaDto(Byte order, CourseMetaDto courseMeta) {
        this.order = order;
        this.courseMeta = courseMeta;
    }
}
