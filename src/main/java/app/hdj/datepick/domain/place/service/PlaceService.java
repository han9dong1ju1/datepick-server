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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceCategoryRelationRepository placeCategoryRelationRepository;

    @PersistenceContext
    private EntityManager em;
    
    public CustomPage<PlaceResponse> getPlacePage(PagingParam pagingParam,
                                                  CustomSort customSort,
                                                  PlaceFilterParam placeFilterParam,
                                                  Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Place> placePage = placeRepository.findPlacePage(placeFilterParam, pagingParam, sort);

        return new CustomPage<>(
                placePage.getTotalElements(),
                placePage.getTotalPages(),
                placePage.getNumber(),
                placePage.getContent().stream().map(
                        place -> PlaceResponse.from(place, userId)
                ).collect(Collectors.toList())
        );
    }

    public CustomPage<PlaceResponse> getPickedPlacePage(PagingParam pagingParam,
                                                        CustomSort customSort,
                                                        PlaceFilterParam placeFilterParam,
                                                        Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Place> pickedPlacePage = placeRepository.findPickedPlacePage(placeFilterParam, pagingParam, sort, userId);
        return new CustomPage<>(
                pickedPlacePage.getTotalElements(),
                pickedPlacePage.getTotalPages(),
                pickedPlacePage.getNumber(),
                pickedPlacePage.getContent().stream().map(
                        place -> PlaceResponse.fromOnlyPicked(place, userId)
                ).collect(Collectors.toList())
        );
    }

    public PlaceResponse getPlace(Long placeId, Long userId) {
        Place place =  placeRepository.findById(placeId).orElseThrow();
        return PlaceResponse.from(place, userId);
    }

    @Transactional
    public PlaceResponse addPlace(PlaceRequest placeRequest, Long userId) {


        //Place
        Place place = Place.builder()
                .kakaoId(placeRequest.getKakaoId())
                .name(placeRequest.getName())
                .address(placeRequest.getAddress())
                .latitude(placeRequest.getLatitude())
                .longitude(placeRequest.getLongitude())
                .pickCount(0L)
                .reviewCount(0L)
                .viewCount(0L)
                .rating(0F)
                .build();

        place = placeRepository.save(place);

        //Category
        List<String> categoryNameList = Arrays.asList(placeRequest.getCategories().replaceAll(" ", "").split(">"));
        List<Category> categoryList = categoryRepository.findCategoryByNameIn(categoryNameList);
        List<String> existCategoryNameList = categoryList.stream().map(Category::getName).collect(Collectors.toList());

        for (String name: categoryNameList) {
            if (!existCategoryNameList.contains(name)) {
                categoryList.add(Category.builder().name(name).placeCount(0L).build());
            }
        }

        categoryList = categoryRepository.saveAll(categoryList);

        //Relation
        List<PlaceCategoryRelation> placeCategoryRelations = new ArrayList<>();
        for (Category category : categoryList) {
            placeCategoryRelations.add(PlaceCategoryRelation.builder().place(place).category(category).build());
        }

        placeCategoryRelationRepository.saveAll(placeCategoryRelations);

        em.refresh(place);

        return PlaceResponse.from(place, userId);
    }

}
