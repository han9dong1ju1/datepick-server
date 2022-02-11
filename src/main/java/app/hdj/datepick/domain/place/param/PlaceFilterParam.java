package app.hdj.datepick.domain.place.param;

import app.hdj.datepick.global.common.FilterParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceFilterParam extends FilterParam{

    private String category;
    private Double latitude;
    private Double longitude;
    private Double distance;

}
