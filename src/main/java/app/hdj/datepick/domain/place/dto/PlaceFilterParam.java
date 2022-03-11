package app.hdj.datepick.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class PlaceFilterParam {

    private String keyword;

    private Double latitude;

    private Double longitude;

    @Positive
    private Double distance;

    private List<@Positive Long> categoryId;

    @Positive
    private Long courseId;

}
