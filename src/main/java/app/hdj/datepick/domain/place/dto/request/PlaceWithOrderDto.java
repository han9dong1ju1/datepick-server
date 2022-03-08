package app.hdj.datepick.domain.place.dto.request;


import app.hdj.datepick.domain.place.entity.Place;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PlaceWithOrderDto {
    private Long courseId;
    private Byte placeOrder;
    private Place place;

    @QueryProjection
    public PlaceWithOrderDto(Long courseId, Byte placeOrder, Place place) {
        this.courseId = courseId;
        this.placeOrder = placeOrder;
        this.place = place;
    }
}
