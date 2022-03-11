package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.domain.place.service.PlaceService;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public CustomPage<Place> getPlacePage(@Valid PagingParam pagingParam,
                                          @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular", "rating_desc", "rating_asc", "distance_asc"}) String sort,
                                          @Valid PlaceFilterParam placeFilterParam) {
        return placeService.getPlacePage(pagingParam, CustomSort.from(sort), placeFilterParam);
    }

    @GetMapping("/{placeId}")
    public Place getPlace(@PathVariable Long placeId) {
        return placeService.getPlace(placeId);
    }

}
