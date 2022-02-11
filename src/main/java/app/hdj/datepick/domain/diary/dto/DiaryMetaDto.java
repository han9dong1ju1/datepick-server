package app.hdj.datepick.domain.diary.dto;

import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DiaryMetaDto {
    private Long id;
    private Long courseId;
    private Long userId;
    private String title;
    private Long likeCount;
    private String style;

    @QueryProjection
    public DiaryMetaDto(Long id, Long courseId, Long userId, String title, Long likeCount, String style) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.title = title;
        this.likeCount = likeCount;
        this.style = style;
    }
}
