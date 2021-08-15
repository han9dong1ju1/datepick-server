package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.CourseEntity;
import app.hdj.datepick.data.entity.FeaturedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFeaturedRepository extends JpaRepository<FeaturedEntity, Long> {

    @Query(value = "SELECT c FROM course c WHERE c.id in " +
            "(SELECT courseId FROM course_featured_relation WHERE featuredId = :featuredId)")
    List<CourseEntity> findCourseListByFeaturedId(@Param("featuredId") Long id);
}
