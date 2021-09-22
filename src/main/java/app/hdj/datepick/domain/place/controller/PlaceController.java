package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestFilterDto;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public PlaceDetailDto getPlace(@PathVariable Long placeId){
        return placeService.getPlace(placeId);
    }

    @GetMapping("/{placeId}/photos")
    public Page<String> getPlacePhotos(@PathVariable Long placeId, Pageable pageable){
        return placeService.getPlaceImagePage(placeId, pageable);
    }

    @GetMapping("")
    public Page<PlaceMetaDto> getPlaces(@RequestParam(value = "request_type") String requestType , Pageable pageable){
        if(requestType.equals("picked")){
            return placeService.getPickedPlacePage(pageable);
        }else if (requestType.equals("recommended")){
            return placeService.getRecommendedPlaceList(pageable);
        }else {
            //TODO RequestParam Validation Exception
            return null;
        }
    }

    @PostMapping("")
    public Place addPlace(@RequestBody PlaceRequestDto placeRequestDto){
        return placeService.addPlace(placeRequestDto);
    }

}
