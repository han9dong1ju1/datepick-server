package app.hdj.datepick.data.query;

import app.hdj.datepick.domain.entity.Course;
import app.hdj.datepick.domain.entity.Featured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFeaturedRepository extends JpaRepository<Featured, Long> {

    @Query(value = "SELECT c FROM course c WHERE c.id in " +
            "(SELECT courseId FROM course_featured_relation WHERE featuredId = :featuredId)")
    List<Course> findCourseListByFeaturedId(@Param("featuredId") Long id);
}
