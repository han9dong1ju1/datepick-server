package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlacePage;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import app.hdj.datepick.domain.place.service.PlaceService;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public CustomPage<PlacePage> getPlacePage(@RequestParam(value = "course_id", required = false) Long courseId,
                                              //PlaceFilterParam placeFilterParam,
                                              @Valid PagingParam pagingParam
                                        ){

        return placeService.getPlacePage(courseId, null, pagingParam);
    }
}
