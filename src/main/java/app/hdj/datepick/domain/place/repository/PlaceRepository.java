package app.hdj.datepick.domain.place.repository;

import app.hdj.datepick.domain.featured.repository.FeaturedCustomRepository;
import app.hdj.datepick.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PlaceRepository extends
        JpaRepository<Place, Long>,
        PlaceCustomRepository,
        QuerydslPredicateExecutor<Place>{

    //jpa delete 쿼리의 경우 select place 를 통해서 이미 존재하는지 여부를 판단하고 delete쿼리를 날림
    //TODO 논의틀 통해서 어떤 메소드를 쓸지 고민
    void deleteById(Long id);
}
