package app.hdj.datepick.global.common.dto;

import app.hdj.datepick.global.common.enums.Category;
import app.hdj.datepick.global.common.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchParamDto {

    String keyword;

    GeoPointDto geoPoint;

    Double rating;

    Category category;

    Tag tag;

}
