package app.hdj.datepick.domain.service;


import app.hdj.datepick.domain.model.Place;
import app.hdj.datepick.domain.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImp implements PlaceService{

    private PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImp(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> getPickedPlaces(Long userId) {
        return placeRepository.findPickedPlaces(userId);
    }

    @Override
    public List<Place> getPlacesWhereInCourse(Long courseId) {
        return placeRepository.findPlacesWhereInCourse(courseId);
    }

    @Override
    public void postPlace(Place place) {
        placeRepository.savePlace(place);
    }
}
