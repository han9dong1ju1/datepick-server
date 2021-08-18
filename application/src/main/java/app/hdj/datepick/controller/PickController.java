package app.hdj.datepick.controller;

import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.repository.PlacePickRepository;
import app.hdj.datepick.domain.service.PickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/pick")
public class PickController {

    private final PickService pickService;

    @Autowired
    public PickController(PickService pickService) {
        this.pickService = pickService;
    }

    @GetMapping("/places/{userId}")
    public List<Place> getPickedPlaces(@PathVariable Long userId){
        /**
         * 요청 user id 로 픽한 places 반환
         */
        return pickService.getPickedPlaces(userId);
    }
}
