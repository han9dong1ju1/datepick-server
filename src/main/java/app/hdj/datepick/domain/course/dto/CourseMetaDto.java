package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.user.dto.UserMetaDto;
import app.hdj.datepick.global.common.enums.Region;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseMetaDto {

    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private Integer pickCount;
    private Integer importCount;
    private Long userId;
    private String thumbNailUrl;

    @QueryProjection
    public CourseMetaDto(Long id, String title, String region, LocalDateTime expectedAt, Integer pickCount, Integer importCount, Long userId) {
        this.id = id;
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
        this.pickCount = pickCount;
        this.importCount = importCount;
        this.userId = userId;
    }
}
