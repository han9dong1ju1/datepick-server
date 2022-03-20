package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.global.enums.CustomSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceCustomRepository {
    Page<PlaceResponse> findPlacePage(PlaceFilterParam placeFilterParam, Pageable pageable, CustomSort customSort);
}
