package app.hdj.datepick.domain.service;

import app.hdj.datepick.domain.model.Place;

import java.util.List;

public interface PickService {
    List<Place> getPickedPlaces(Long userId);
}
