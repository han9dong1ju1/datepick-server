package app.hdj.datepick.domain.course.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseDetailDto {

    private Long id;
    private String title;
    private String region;
    private LocalDateTime expectedAt;
    private Integer pickCount;
    private Integer importCount;
    private Long userId;
    private String thumbNailUrl;

    private Boolean isPicked;
    //Place Relatation Info
    private List<CoursePlaceRelationDto> placeRelations;

    @QueryProjection
    public CourseDetailDto(Long id, String title, String region, LocalDateTime expectedAt, Integer pickCount, Integer importCount, Long userId, String thumbNailUrl, Boolean isPicked, List<CoursePlaceRelationDto> placeRelations) {
        this.id = id;
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
        this.pickCount = pickCount;
        this.importCount = importCount;
        this.userId = userId;
        this.thumbNailUrl = thumbNailUrl;
        this.isPicked = isPicked;
        this.placeRelations = placeRelations;
    }
}

