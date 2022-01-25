package app.hdj.datepick.domain.place.dto;

import app.hdj.datepick.domain.diary.dto.PlaceReviewDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceDetailDto {

    //TODO region 추가
    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private Category categorys;

    private Boolean isPicked;

    private List<PlaceReviewDto> reviews;
    @Getter
    @AllArgsConstructor
    class Category{
        private String type;
        private String subtype;
        private String category;
    }

    @QueryProjection
    public PlaceDetailDto(Long id, String kakaoId, String name, Float rating, String address, Double latitude, Double longitude, String type, String subtype, String category, Boolean isPicked, List<PlaceReviewDto> reviews) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categorys = new Category(type, subtype, category);
        this.isPicked = isPicked;
        this.reviews = reviews;
    }
}
