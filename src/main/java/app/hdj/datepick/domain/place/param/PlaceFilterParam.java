package app.hdj.datepick.domain.place.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class PlaceFilterParam {

    private String keyword;
    private String category;
    private Double latitude;
    private Double longitude;
    @Positive
    private Double distance;

}
