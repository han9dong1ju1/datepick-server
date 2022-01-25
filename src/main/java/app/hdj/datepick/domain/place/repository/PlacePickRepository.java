package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.place.entity.PlacePick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface PlacePickRepository extends
        JpaRepository<PlacePick, Long>,
        PickCustomRepository,
        QuerydslPredicateExecutor<PlacePick> {
}
