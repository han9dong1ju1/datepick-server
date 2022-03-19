package app.hdj.datepick.domain.relation.repository;

import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTagRepository extends JpaRepository<CourseTagRelation, Long> {
}
