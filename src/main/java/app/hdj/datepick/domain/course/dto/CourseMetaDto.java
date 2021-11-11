package app.hdj.datepick.domain.course.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

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
