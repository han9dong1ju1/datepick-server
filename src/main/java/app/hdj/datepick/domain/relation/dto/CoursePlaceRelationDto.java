package app.hdj.datepick.domain.relation.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoursePlaceRelationDto {
    private Long courseId;
    private Long placeId;
    private Byte placeOrder;
    private LocalDateTime visitTime;
    private String memo;

    @QueryProjection
    public CoursePlaceRelationDto(Long courseId, Long placeId, Byte placeOrder, LocalDateTime visitTime, String memo) {
        this.courseId = courseId;
        this.placeId = placeId;
        this.placeOrder = placeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }
}
