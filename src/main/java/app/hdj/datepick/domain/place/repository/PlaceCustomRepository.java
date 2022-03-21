package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.PagingParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface PlaceCustomRepository {
    Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, Sort sort);
    Page<Place> findPickedPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, Sort sort, Long userId);
}
