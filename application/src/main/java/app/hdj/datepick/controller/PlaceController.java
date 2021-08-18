package app.hdj.datepick.controller;


import app.hdj.datepick.domain.model.Place;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/places")
public class PlaceController {

    @GetMapping("/{placeId}")
    public Place getPlace(@PathVariable Long placeId){
        /**
         * params : Long placeId
         * return : place
         * description :
         * 요청 place id 로 placeModel 객체 1개 반환
         */

        Place place = new Place(
        1L,
        1000L,
        "이름",
        4.5F,
        "주소",
        123.123,
        123.123,
        null,
        false
        );
        return place;
    }

    @GetMapping("/textsearch")
    public void textSearchPlaces(@RequestParam(value = "query") String query,
                                 @RequestParam(value = "sort", required = false, defaultValue = "accuracy") String sort)
    {
        /**
         * search places by query
         * sorting by sort (ex "rating, distance")
         */
        return;
    }

    @GetMapping("/nearbysearch")
    public void nearBySearchPlaces(@RequestParam(value = "latitude") double latitude,
                             @RequestParam(value = "longitude")  double longitude,
                             @RequestParam(value = "sort", required = false, defaultValue = "distance") String sort)
    {
        /**
         * search places by location
         * default sort : "distance", not required sort param
         */
        return;
    }

    @GetMapping("/course/{courseId}")
    public void courseSearchPlaces(@PathVariable Long courseId)
    {
        /**
         * find places included course
         */
        return;
    }


}
