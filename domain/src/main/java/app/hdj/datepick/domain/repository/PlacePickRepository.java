package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.model.Place;

import java.util.List;

public interface PlacePickRepository {

    public List<Place> findPickedPlaces(Long userId);
}
