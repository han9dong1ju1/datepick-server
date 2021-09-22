package app.hdj.datepick.domain.place.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceMetaDto {
    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private Category categorys;

    @Getter
    @AllArgsConstructor
    class Category{
        private String type;
        private String subtype;
        private String category;
    }

    @QueryProjection
    public PlaceMetaDto(Long id, String kakaoId, String name, Float rating, String address, Double latitude, Double longitude, String type, String subtype, String category) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categorys = new PlaceMetaDto.Category(type, subtype, category);
    }
}
