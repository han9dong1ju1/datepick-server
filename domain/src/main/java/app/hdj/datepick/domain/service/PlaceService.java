package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.Place;

import java.util.List;

public interface PlaceService {
    List<Place> getPickedPlaces(Long userId);
    List<Place> getPlacesWhereInCourse(Long courseId);
    void postPlace(Place place);
}
