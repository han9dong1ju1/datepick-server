package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

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


}
