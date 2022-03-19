package app.hdj.datepick.domain.relation.repository;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePlaceRepository extends JpaRepository<CoursePlaceRelation, Long> {
}
