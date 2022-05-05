package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import org.springframework.data.domain.Page;

public interface PlaceCustomRepository {

    Page<Place> findPlacePage(
        PlaceFilterParam placeFilterParam, PagingParam pagingParam, CustomSort sort
    );

    Page<Place> findPickedPlacePage(
        PlaceFilterParam placeFilterParam, PagingParam pagingParam, CustomSort sort, Long userId
    );
}
