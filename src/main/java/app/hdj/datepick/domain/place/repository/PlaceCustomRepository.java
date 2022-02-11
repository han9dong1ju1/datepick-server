package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlacePage;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceCustomRepository {

    Page<PlacePage> findPlacePage(Long courseId, PlaceFilterParam placeFilterParam, Pageable pageable);

}
