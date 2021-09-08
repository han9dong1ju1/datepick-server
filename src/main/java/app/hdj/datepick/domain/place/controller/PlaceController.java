package app.hdj.datepick.domain.place.controller;

import app.hdj.datepick.domain.place.dto.response.PlaceMetaDto;
import app.hdj.datepick.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public PlaceMetaDto getPlace(@PathVariable Long placeId){
        return placeService.getPlace(placeId);
    }

}
