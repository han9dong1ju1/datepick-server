package app.hdj.datepick.domain.course.repository;

import app.hdj.datepick.domain.course.dto.CourseDetailDto;
import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.dto.CoursePlaceDetailRelationDto;
import app.hdj.datepick.domain.course.dto.request.ModifyCoursePlaceRelationDto;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.dto.CoursePlaceRelationDto;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
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
    List<CoursePlaceDetailRelationDto> findPlaceRelationDtoInCourse(Long courseId, List<Long> placeIds);

    /**
     *
     * @param courseId
     * @param isPicked
     * @param placeRelations
     * @return
     */
    CourseDetailDto findCourseDetail(Long courseId, Boolean isPicked, List<CoursePlaceDetailRelationDto> placeRelations);


    List<CoursePlaceRelation> findPlaceRelationByCourseId(Long courseId);
    List<CoursePlaceRelationDto> findPlaceRelationDtoByCourseId(Long courseId);

    void updatePlaceRelations(Long courseId, ModifyCoursePlaceRelationDto placeRelation);

    void insertPlaceRelations(Long courseId, ModifyCoursePlaceRelationDto placeRelation);

    Long deletePlaceRelations(Long courseId, List<Long> newPlaceIds);

    void createCoursePlaceRelation(Long courseId, List<ModifyCoursePlaceRelationDto> placeRelations);
}
