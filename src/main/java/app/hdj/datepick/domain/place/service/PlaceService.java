package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.category.entity.Category;
import app.hdj.datepick.domain.category.repository.CategoryRepository;
import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceRequest;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.domain.relation.repository.PlaceCategoryRelationRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceCategoryRelationRepository placeCategoryRelationRepository;

    public CustomPage<PlaceResponse> getPlacePage(
        PagingParam pagingParam,
        CustomSort customSort,
        PlaceFilterParam placeFilterParam,
        Long userId
    ) {
        Page<Place> placePage = placeRepository.findPlacePage(placeFilterParam, pagingParam,
                                                              customSort);
        return new CustomPage<>(placePage.getTotalElements(), placePage.getTotalPages(),
                                placePage.getNumber(), placePage.getContent().stream()
                                    .map(place -> PlaceResponse.from(place, userId))
                                    .collect(Collectors.toList()));
    }

    public CustomPage<PlaceResponse> getPickedPlacePage(
        PagingParam pagingParam,
        CustomSort customSort,
        PlaceFilterParam placeFilterParam,
        Long userId
    ) {
        Page<Place> pickedPlacePage = placeRepository.findPickedPlacePage(placeFilterParam,
                                                                          pagingParam, customSort,
                                                                          userId);
        return new CustomPage<>(pickedPlacePage.getTotalElements(), pickedPlacePage.getTotalPages(),
                                pickedPlacePage.getNumber(), pickedPlacePage.getContent().stream()
                                    .map(place -> PlaceResponse.from(place, userId))
                                    .collect(Collectors.toList()));
    }

    @Transactional
    public PlaceResponse getPlace(Long placeId, Long userId, boolean alreadyViewed) {
        Place place = placeRepository.findById(placeId).orElseThrow();

        if (!alreadyViewed) {
            place.increaseView();
        }

        return PlaceResponse.from(place, userId);
    }

    @Transactional
    public PlaceResponse addPlace(PlaceRequest placeRequest) {
        Place place = Place.builder().kakaoId(placeRequest.getKakaoId())
            .name(placeRequest.getName()).address(placeRequest.getAddress())
            .latitude(placeRequest.getLatitude()).longitude(placeRequest.getLongitude()).build();
        final Place finalPlace = placeRepository.save(place);

        List<String> categoryNames = Arrays.asList(placeRequest.getCategories().split(" > "));
        List<Category> categories = categoryRepository.findCategoryByNameIn(categoryNames);
        List<String> existingNames = categories.stream().map(Category::getName)
            .collect(Collectors.toList());
        categories.addAll(categoryNames.stream().filter(name -> !existingNames.contains(name))
                              .map(name -> Category.builder().name(name).build())
                              .collect(Collectors.toList()));
        categories = categoryRepository.saveAll(categories);

        List<PlaceCategoryRelation> placeCategoryRelations = categories.stream().map(
            category -> PlaceCategoryRelation.builder().place(finalPlace).category(category)
                .build()).collect(Collectors.toList());

        placeCategoryRelationRepository.saveAll(placeCategoryRelations);
        place.setPlaceCategories(placeCategoryRelations);

        return PlaceResponse.from(place);
    }
}
