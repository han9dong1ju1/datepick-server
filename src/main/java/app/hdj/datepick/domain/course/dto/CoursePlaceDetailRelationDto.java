package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@ToString
@Getter
public class CoursePlaceDetailRelationDto {

    private Byte placeOrder;
    private Time visitTime;
    private String memo;
    private PlaceDto placeMeta;

    @QueryProjection
    public CoursePlaceDetailRelationDto(Byte placeOrder, Time visitTime, String memo, PlaceDto placeMeta) {
        this.placeOrder = placeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
        this.placeMeta = placeMeta;
    }
}
