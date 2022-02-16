package app.hdj.datepick.domain.diary.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
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
