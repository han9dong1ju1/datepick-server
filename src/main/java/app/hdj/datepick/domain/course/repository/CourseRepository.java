package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CourseRepository extends
        JpaRepository<Course, Long>,
        CourseCustomRepository,
        QuerydslPredicateExecutor<Course> {

    void deleteById(Long courseId);

}
