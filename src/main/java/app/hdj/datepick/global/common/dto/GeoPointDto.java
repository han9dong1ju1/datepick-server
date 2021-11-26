package app.hdj.datepick.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class GeoPointDto {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Double distanceLimit;

}
