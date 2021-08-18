package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.PlaceEntity;
import app.hdj.datepick.data.entity.PlacePickEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPlacePickRepository extends JpaRepository<PlacePickEntity, Long> {

    @Query(value = "SELECT p FROM place p WHERE p.id in " +
            "(SELECT placeId FROM place_pick WHERE userId = :userId)")
    List<PlaceEntity> findPickedPlacesByUserId(Long userId);
}
