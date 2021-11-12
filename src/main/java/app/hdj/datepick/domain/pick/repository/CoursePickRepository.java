package app.hdj.datepick.domain.pick.repository;

import app.hdj.datepick.domain.pick.entity.CoursePick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CoursePickRepository extends
        JpaRepository<CoursePick, Long>,
        PickCustomRepository,
        QuerydslPredicateExecutor<CoursePick> {
}
