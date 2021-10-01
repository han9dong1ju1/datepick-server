package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.dto.CourseDetailDto;
import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.dto.CoursePlaceRelationDto;
import app.hdj.datepick.domain.featured.dto.FeaturedCourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseCustomRepository{

    /**
     *
     * @param courseId
     * @return
     */
    List<Long> findPlaceIdListInCourse(Long courseId);


    /**
     *
     * @param courseIds
     * @param pageable
     * @return
     */
    Page<CourseMetaDto> findCourseMetaPageById(List<Long> courseIds, Pageable pageable);

    /**
     *
     * @param courseIds
     * @return
     */
    List<CourseMetaDto> findCourseMetaListById(List<Long> courseIds);

    /**
     *
     * @param courseId
     * @param placeIds
     * @return
     */
    List<CoursePlaceRelationDto> findPlaceRelationInCourse(Long courseId, List<Long> placeIds);

    /**
     *
     * @param courseId
     * @param isPicked
     * @param placeRelations
     * @return
     */
    CourseDetailDto findCourseDetail(Long courseId, Boolean isPicked, List<CoursePlaceRelationDto> placeRelations);

}
