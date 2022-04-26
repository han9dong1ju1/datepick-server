package app.hdj.datepick.domain.place.dto;


import app.hdj.datepick.domain.category.dto.CategorySimpleResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceResponse {

    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private List<CategorySimpleResponse> categories;

    private Boolean isPicked;
    private Long viewCount;
    private Long reviewCount;
    private Long pickCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static PlaceResponse from(Place place, Long userId) {
        return PlaceResponse.builder()
                .id(place.getId())
                .kakaoId(place.getKakaoId())
                .name(place.getName())
                .rating(place.getRating())
                .address(place.getAddress())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .viewCount(place.getViewCount())
                .reviewCount(place.getPlaceCourses().stream().map(CoursePlaceRelation::getDiary).filter(Objects::nonNull).count())
                .pickCount((long) place.getPlacePicks().size())
                .createdAt(place.getCreatedAt())
                .updatedAt(place.getUpdatedAt())
                .isPicked(userId != null && place.getPlacePicks().stream().anyMatch(placePick -> placePick.getUser().getId().equals(userId)))
                .categories(place.getPlaceCategories().stream().map(
                        categoryRelation -> CategorySimpleResponse.from(categoryRelation.getCategory())
                ).collect(Collectors.toList())).build();
    }

}
