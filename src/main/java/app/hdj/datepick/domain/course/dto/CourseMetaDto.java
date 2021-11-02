package app.hdj.datepick.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CourseMetaDto {
    Long id;
    String title;
    Integer pickCount;
    Integer importCount;
    Long userId;

    @QueryProjection
    public CourseMetaDto(Long id, String title, Integer pickCount, Integer importCount, Long userId) {
        this.id = id;
        this.title = title;
        this.pickCount = pickCount;
        this.importCount = importCount;
        this.userId = userId;
    }
}
