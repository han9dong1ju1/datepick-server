package app.hdj.datepick.domain.place.dto;


import app.hdj.datepick.domain.category.dto.CategorySimpleResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static PlaceResponse from(Place place) {
        return placeResponseBuilder(place)
                .isPicked(false)
                .build();
    }

    public static PlaceResponse from(Place place, Long userId) {
        return placeResponseBuilder(place)
                .isPicked(userId != null && Optional.ofNullable(place.getPlacePicks())
                        .orElseGet(Collections::emptyList)
                        .stream().anyMatch(placePick -> placePick.getUser().getId().equals(userId)))
                .build();
    }

    private static PlaceResponseBuilder placeResponseBuilder(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .kakaoId(place.getKakaoId())
                .name(place.getName())
                .rating(place.getRating())
                .address(place.getAddress())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .viewCount(place.getViewCount())
                .reviewCount(Optional.ofNullable(place.getPlaceCourses())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(CoursePlaceRelation::getDiary)
                        .filter(Objects::nonNull)
                        .count())
                .pickCount((long) Optional.ofNullable(place.getPlacePicks())
                        .orElseGet(Collections::emptyList)
                        .size())
                .createdAt(place.getCreatedAt())
                .updatedAt(place.getUpdatedAt())
                .categories(place.getPlaceCategories().stream().map(
                        categoryRelation -> CategorySimpleResponse.from(categoryRelation.getCategory())
                ).collect(Collectors.toList()));
    }

}
