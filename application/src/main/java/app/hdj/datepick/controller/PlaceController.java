package app.hdj.datepick.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/places")
public class PlaceController {

    @GetMapping("/nearbysearch")
    public void getPlacesNearbySearch(double x, double y){
        /**
         * params :
         * double latitude
         * double longitude
         * return :
         * List<place> order by nearest
         */
        return;
    }
    @GetMapping("/nearbysearch")
    public void getPlace(double x, double y){
        /**
         * params :
         * double latitude
         * double longitude
         * return :
         * List<place> order by nearest
         */
        return;
    }


}
