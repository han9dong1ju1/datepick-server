package app.hdj.datepick.controller;


import app.hdj.datepick.filter.PlaceParamFilter;
import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/{placeId}")
    public Place getPlace(@PathVariable Long placeId){
        /**
         * description :
         * 요청 place id 로 placeModel 객체 1개 반환
         */
        return null;
    }

    @GetMapping("")
    public List<Place> getPlaces(@ModelAttribute PlaceParamFilter placeParamFilter)
    {
        List<Place> places;
        if (placeParamFilter.isPick()){
            places = placeService.getPickedPlaces(placeParamFilter.getUserId());
        }
        else {
            places = placeService.getPlacesWhereInCourse(placeParamFilter.getCourseId());
        }
        return places;
    }



}
