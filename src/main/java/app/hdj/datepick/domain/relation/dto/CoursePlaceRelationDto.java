package app.hdj.datepick.domain.relation.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.sql.Time;

@Getter
public class CoursePlaceRelationDto {
    private Long courseId;
    private Long placeId;
    private Byte placeOrder;
    private Time visitTime;
    private String memo;

    @QueryProjection
    public CoursePlaceRelationDto(Long courseId, Long placeId, Byte placeOrder, Time visitTime, String memo) {
        this.courseId = courseId;
        this.placeId = placeId;
        this.placeOrder = placeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
    }
}
