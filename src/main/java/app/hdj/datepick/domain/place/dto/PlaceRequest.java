package app.hdj.datepick.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {

    @NotNull
    private String kakaoId;
    @NotNull
    private String name;
    @NotNull
    private String categories;
    @NotNull
    private String address;

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

}
