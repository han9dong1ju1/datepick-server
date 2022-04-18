package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PlaceRepository extends
        JpaRepository<Place, Long>,
        PlaceCustomRepository,
        QuerydslPredicateExecutor<Place> {
}
