package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestFilterDto;
import app.hdj.datepick.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/photos/{placeId}")
    public Page<String> getPlacePhotos(Long placeId, Pageable pageable){
        return placeService.getPlaceImagePage(placeId, pageable);
    }
    
    @GetMapping("")
    public Page<PlaceMetaDto> getPlaces(@PathVariable String requestType , Pageable pageable){
        if(requestType == "PickedPlace"){
            return placeService.getPickedPlacePage(pageable);
        }else if (requestType == "RecommededPlace"){
            return placeService.getRecommendedPlaceList(pageable);
        }else {
            return null;
        }
    }

    @PostMapping("")
    public PlaceRequestDto addPlace(@RequestBody PlaceRequestDto placeRequestDto){
        log.debug("POST PLACE :" + placeRequestDto.toString());
        return placeService.addPlace(placeRequestDto);
    }

}
