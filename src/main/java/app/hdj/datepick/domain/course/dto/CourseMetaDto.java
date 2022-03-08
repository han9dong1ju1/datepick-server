package app.hdj.datepick.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CourseMetaDto {

    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private Long pickCount;
    private Long userId;
    private String thumbNailUrl;

    @QueryProjection
    public CourseMetaDto(Long id, String title, String region, LocalDateTime expectedAt, Long pickCount, Long userId) {
        this.id = id;
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
        this.pickCount = pickCount;
        this.userId = userId;
    }
}
