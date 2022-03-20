package app.hdj.datepick.domain.place.dto;


import app.hdj.datepick.domain.category.dto.CategoryResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PlaceResponse {

    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private List<CategoryResponse> categories;

    private Boolean isPicked;
    private Long viewCount;
    private Long reviewCount;
    private Long pickCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public PlaceResponse(Place place){
        this.id = place.getId();
        this.kakaoId = place.getKakaoId();
        this.name = place.getName();
        this.rating = place.getRating();
        this.address = place.getAddress();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();

        this.isPicked = place.getIsPicked();
        this.viewCount = place.getViewCount();
        this.reviewCount = place.getReviewCount();
        this.pickCount = place.getPickCount();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();

        this.categories = place.getPlaceCategories().stream().map(placeCategoryRelation -> {
            return new CategoryResponse(placeCategoryRelation.getCategory());
        }).collect(Collectors.toList());

    }
}
