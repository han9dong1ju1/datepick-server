package app.hdj.datepick.data.query;

import app.hdj.datepick.data.entity.CourseTable;
import app.hdj.datepick.data.entity.FeaturedTable;
import app.hdj.datepick.domain.dto.FeaturedDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFeaturedRepository extends JpaRepository<FeaturedTable, Long> {

    @Query(value = "Select c From course c Where c.id in " +
            "(SELECT course_id FROM course_featured_relation WHERE featured_id = :featuredId)")
    public List<CourseTable> findCourseListByFeaturedId(@Param("featuredId") Long id);
}
