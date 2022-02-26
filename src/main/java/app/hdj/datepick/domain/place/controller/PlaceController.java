package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceDto;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.domain.place.service.PlaceService;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.enums.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public CustomPage<PlaceDto> getPlacePage(@RequestParam(value = "course_id", required = false) Long courseId,
                                             @ValueOfEnum(enumClass = CustomSort.class,
                                                     acceptedValues = {"latest", "pick", "popular", "rating_desc", "rating_asc", "distance_asc"}) String sort,
                                             @Valid PlaceFilterParam placeFilterParam,
                                             @Valid PagingParam pagingParam){
        return placeService.getPlacePage(courseId, CustomSort.from(sort), placeFilterParam, pagingParam);
    }

    @GetMapping("/{placeId}")
    public PlaceDto getPlace(@PathVariable Long placeId) {
        return placeService.getPlace(placeId);
    }

}
