package app.hdj.datepick.domain.place.service;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public CustomPage<Place> getPlacePage(PagingParam pagingParam,
                                             CustomSort customSort,
                                             PlaceFilterParam placeFilterParam) {
        Sort sort = CustomSort.toSort(customSort);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(), sort);
        Page<Place> placePage = placeRepository.findPlacePage(placeFilterParam, pageRequest);

        return new CustomPage<>(
                placePage.getTotalElements(),
                placePage.getTotalPages(),
                placePage.getNumber(),
                placePage.getContent()
        );
    }

    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow();
    }

}
