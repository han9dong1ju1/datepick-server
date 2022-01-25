package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.entity.CoursePick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CoursePickRepository extends
        JpaRepository<CoursePick, Long>,
        PickCustomRepository2,
        QuerydslPredicateExecutor<CoursePick> {
}
