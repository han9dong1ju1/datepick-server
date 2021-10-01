package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoursePlaceRelationDto {

    private Byte placeOrder;
    private LocalDateTime visitTime;
    private String memo;
    private PlaceMetaDto placeMeta;

    @QueryProjection
    public CoursePlaceRelationDto(Byte placeOrder, LocalDateTime visitTime, String memo, PlaceMetaDto placeMeta) {
        this.placeOrder = placeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
        this.placeMeta = placeMeta;
    }
}
