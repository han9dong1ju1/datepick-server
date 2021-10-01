package app.hdj.datepick.domain.course.service;

import app.hdj.datepick.domain.course.dto.CourseDetailDto;
import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.dto.CoursePlaceRelationDto;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.pick.entity.CoursePick;
import app.hdj.datepick.domain.pick.repository.CoursePickRepository;
import app.hdj.datepick.domain.place.dto.PlaceMetaDto;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CoursePickRepository coursePickRepository;

    // TODO: 파라미터 선정 및 구현
    public void getCourseList() {}

    // TODO: 파라미터 선정 및 구현
    public void getCoursePage() {}


    public Page<CourseMetaDto> getPickedCoursePage(Pageable pageable) {
        //TODO User Id
        Long userId = 10L;
        //
        List<Long> courseIds = coursePickRepository.findPickedCourseIds(userId);
        //
        Page<CourseMetaDto> courseMetaPage = courseRepository.findCourseMetaPageById(courseIds, pageable);

        return courseMetaPage;
    }

    // TODO: 파라미터 선정 및 구현
    public void getRandomCourse() {

    }

    public CourseDetailDto getCourse(Long courseId) {
        //TODO User Id
        Long userId = 10L;
        //user의 course pick 여부
        Boolean isPicked = coursePickRepository.isUserPickedCourse(courseId, userId);
        //course에 포함된 place id list
        List<Long> placeIds = courseRepository.findPlaceIdListInCourse(courseId);
        //place 와 course의 relation 정보 list
        List<CoursePlaceRelationDto> placeRelations = courseRepository.findPlaceRelationInCourse(courseId, placeIds);
        //course meta 정보 조회 및 detail 정보 조립
        CourseDetailDto courseDetail = courseRepository.findCourseDetail(courseId, isPicked, placeRelations);

        return courseDetail;

    }

    // TODO: 파라미터 선정 및 구현
    public void removeCourse(Long courseId) {

    }

    // TODO: 파라미터 선정 및 구현
    public void modifyCourse(Long id) {

    }

}
