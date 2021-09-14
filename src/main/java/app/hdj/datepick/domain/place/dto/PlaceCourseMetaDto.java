package app.hdj.datepick.domain.place.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlaceCourseMetaDto {

    private Byte placeOrder;
    private LocalDateTime visitTime;
    private String memo;
    private PlaceMetaDto placeMetaDto;

    @QueryProjection
    public PlaceCourseMetaDto(Byte placeOrder, LocalDateTime visitTime, String memo, PlaceMetaDto placeMetaDto) {
        this.placeOrder = placeOrder;
        this.visitTime = visitTime;
        this.memo = memo;
        this.placeMetaDto = placeMetaDto;
    }
}
