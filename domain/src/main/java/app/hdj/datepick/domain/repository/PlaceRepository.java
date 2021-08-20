package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.model.Place;

import java.util.List;

public interface PlaceRepository {

    List<Place> findPlacesWhereInCourse(Long courseId);
    List<Place> findPickedPlaces(Long userId);

}
