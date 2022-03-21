package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.dto.PlaceFilterParam;
import app.hdj.datepick.domain.place.dto.PlaceResponse;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface PlaceCustomRepository {
    Page<Place> findPlacePage(PlaceFilterParam placeFilterParam, PagingParam pagingParam, Sort sort);
}
