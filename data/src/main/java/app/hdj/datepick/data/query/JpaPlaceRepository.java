package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
