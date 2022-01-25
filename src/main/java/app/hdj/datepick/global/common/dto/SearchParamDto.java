package app.hdj.datepick.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchParamDto {

    String keyword;

    GeoPointDto geoPoint;

    Double rating;

    String category;

    // TODO: Tags

}
