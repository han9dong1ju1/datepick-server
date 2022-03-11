package app.hdj.datepick.domain.place.dto;


import app.hdj.datepick.domain.place.entity.Place;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaceDto {

    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private List<CategoryDto> categories;

    private Boolean isPicked;
    private Long viewCount;
    private Long reviewCount;
    private Long pickCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @QueryProjection
    public PlaceDto(Place place, List<CategoryDto> categories) {
        this.id = place.getId();
        this.kakaoId = place.getKakaoId();
        this.name = place.getName();
        this.rating = place.getRating();
        this.address = place.getAddress();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();

        this.categories = categories;

        this.viewCount = place.getViewCount();
        this.reviewCount = place.getReviewCount();
        this.pickCount = place.getPickCount();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();

    }
}
