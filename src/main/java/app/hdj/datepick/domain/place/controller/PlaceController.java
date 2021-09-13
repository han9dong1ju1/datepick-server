package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceInfoRequestDto;
import app.hdj.datepick.domain.place.filter.PlaceRequestFilter;
import app.hdj.datepick.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public PlaceDetailDto getPlace(@PathVariable Long placeId){
        return placeService.getPlace(placeId);
    }

    //TODO request dto 방식 선정
    @PatchMapping("/{placeId}")
    public void patchPlace(@ModelAttribute PlaceInfoRequestDto placeInfoRequestDto){
        log.debug(placeInfoRequestDto.toString());
    }

//    @DeleteMapping("/{placeId}")
//    public void deletePlace(@PathVariable Long placeId){
//        placeService.deletePlace(placeId);
//    }


    @GetMapping("")
    public List<PlaceMetaDto> getPlaces(@ModelAttribute PlaceRequestFilter placeRequestFilter){
        return null;
    }


    //TODO request dto 방식 선정
    @PostMapping("")
    public void postPlace(@RequestBody PlaceInfoRequestDto placeInfoRequestDto){
        log.debug(placeInfoRequestDto.toString());
    }



}
