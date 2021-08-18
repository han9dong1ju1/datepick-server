package app.hdj.datepick.domain.service;


import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.repository.PlacePickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickServiceImp implements PickService{

    private final PlacePickRepository placePickRepository;
    //private CoursePickRepository coursePickRepository;

    @Autowired
    public PickServiceImp(PlacePickRepository placePickRepository) {
        this.placePickRepository = placePickRepository;
    }

    @Override
    public List<Place> getPickedPlaces(Long userId) {
        return placePickRepository.findPickedPlaces(userId);
    }
}
