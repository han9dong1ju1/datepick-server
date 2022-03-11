package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceCustomRepository {
    Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, Pageable pageable);
}
