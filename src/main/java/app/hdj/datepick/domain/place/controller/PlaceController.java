package app.hdj.datepick.domain.place.controller;

import static app.hdj.datepick.global.util.ViewUtil.getAlreadyViewed;
import static app.hdj.datepick.global.util.ViewUtil.setViewCookie;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceRequest;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.service.PlaceService;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public CustomPage<PlaceResponse> getPlacePage(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular",
            "rating_desc", "rating_asc", "distance"}) String sort,
        @Valid PlaceFilterParam placeFilterParam
    ) {
        return placeService.getPlacePage(pagingParam,
                                         CustomSort.from(sort),
                                         placeFilterParam,
                                         userId);
    }

    @GetMapping("/{placeId}")
    public PlaceResponse getPlace(
        @AuthPrincipal Long userId,
        @PathVariable Long placeId,
        HttpServletResponse response,
        @CookieValue(name = "place_view", required = false, defaultValue = "/") String cookieValue
    ) {
        boolean alreadyViewed = getAlreadyViewed(cookieValue, placeId);
        setViewCookie(alreadyViewed, "place_view", cookieValue, placeId, response);
        return placeService.getPlace(placeId, userId, alreadyViewed);
    }

    @Authorize({Role.USER})
    @GetMapping("/picked")
    public CustomPage<PlaceResponse> getPlacePicked(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular",
            "rating_desc", "rating_asc", "distance"}) String sort,
        @Valid PlaceFilterParam placeFilterParam
    ) {
        return placeService.getPickedPlacePage(pagingParam,
                                               CustomSort.from(sort),
                                               placeFilterParam,
                                               userId);
    }

    @Authorize({Role.USER})
    @PostMapping("")
    public PlaceResponse addPlace(@Valid @RequestBody PlaceRequest placeRequest) {
        return placeService.addPlace(placeRequest);
    }
}
