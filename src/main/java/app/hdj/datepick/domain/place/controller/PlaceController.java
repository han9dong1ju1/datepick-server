package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.PlaceDetailDto;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.place.dto.request.PlaceRequestFilterDto;
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

    @GetMapping("")
    public List<PlaceMetaDto> getPlaces(@ModelAttribute PlaceRequestFilterDto placeRequestFilterDto){
        return null;
    }


    @PostMapping("")
    public void addPlace(@RequestBody PlaceRequestDto placeRequestDto){
        log.debug(placeRequestDto.toString());
    }



}
