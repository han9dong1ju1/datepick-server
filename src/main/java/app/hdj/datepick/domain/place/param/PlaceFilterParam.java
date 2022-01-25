package app.hdj.datepick.domain.place.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceFilterParam {

    private String category;
    private Double latitude;
    private Double longitude;

}
