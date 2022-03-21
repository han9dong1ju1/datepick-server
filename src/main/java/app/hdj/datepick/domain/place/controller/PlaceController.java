package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceRequest;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.service.PlaceService;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public CustomPage<PlaceResponse> getPlacePage(@AuthenticationPrincipal Long userId,
                                                  @Valid PagingParam pagingParam,
                                                  @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular", "rating_desc", "rating_asc", "distance_asc"}) String sort,
                                                  @Valid PlaceFilterParam placeFilterParam) {

        return placeService.getPlacePage(pagingParam, CustomSort.from(sort), placeFilterParam, userId);
    }

    @GetMapping("/{placeId}")
    public PlaceResponse getPlace(@AuthenticationPrincipal Long userId,
                                  @PathVariable Long placeId) {
        return placeService.getPlace(placeId, userId);
    }

    @GetMapping("/picked")
    public CustomPage<PlaceResponse> getPlacePicked(@AuthenticationPrincipal Long userId,
                                                    @Valid PagingParam pagingParam,
                                                    @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular", "rating_desc", "rating_asc", "distance_asc"}) String sort,
                                                    @Valid PlaceFilterParam placeFilterParam
                                                    ) {
        return placeService.getPlacePage(pagingParam, CustomSort.from(sort), placeFilterParam, userId);
    }

}
