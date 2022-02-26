package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceDto;
import app.hdj.datepick.domain.place.param.PlaceFilterParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceCustomRepository {

    Page<PlaceDto> findPlacePage(Long courseId, PlaceFilterParam placeFilterParam, Pageable pageable);
    PlaceDto findPlace(Long placeId);
}
