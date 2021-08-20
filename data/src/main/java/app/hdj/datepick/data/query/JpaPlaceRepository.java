package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaPlaceRepository extends JpaRepository<PlaceEntity, Long> {

    @Query(value = "SELECT p FROM place p WHERE p.id in " +
            "(SELECT placeId FROM course_place_relation WHERE courseId = :courseId)")
    List<PlaceEntity> findPlacesWhereInCourse(@Param("courseId") Long courseId);

    @Query(value = "SELECT p FROM place p WHERE p.id in " +
            "(SELECT placeId FROM place_pick WHERE userId = :userId)")
    List<PlaceEntity> findPickedPlaces(@Param("userId") Long userId);


}
