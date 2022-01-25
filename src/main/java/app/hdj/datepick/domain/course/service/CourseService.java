package app.hdj.datepick.domain.course.service;

import app.hdj.datepick.domain.course.dto.CourseDetailDto;
import app.hdj.datepick.domain.course.dto.CourseMetaDto;
import app.hdj.datepick.domain.course.dto.CoursePlaceDetailRelationDto;
import app.hdj.datepick.domain.course.dto.CourseModifyRequsetDto;
import app.hdj.datepick.domain.course.dto.ModifyCourseDto;
import app.hdj.datepick.domain.course.dto.ModifyCoursePlaceRelationDto;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.course.repository.CoursePickRepository;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.place.repository.PlaceRepository;
import app.hdj.datepick.domain.relation.repository.CoursePlaceRelationRepository;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.common.enums.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CoursePickRepository coursePickRepository;
    private final CoursePlaceRelationRepository coursePlaceRelationRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

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
        List<CoursePlaceDetailRelationDto> placeRelations = courseRepository.findPlaceRelationDtoInCourse(courseId, placeIds);
        //course meta 정보 조회 및 detail 정보 조립
        CourseDetailDto courseDetail = courseRepository.findCourseDetail(courseId, isPicked, placeRelations);

        return courseDetail;

    }

    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public CourseDetailDto addCourse(CourseModifyRequsetDto courseModifyRequsetDto){

        //TODO User Id
        Long userId = 10L;
        User user = userRepository.findById(userId).orElseThrow(); //TODO exception

        //코스 생성
        ModifyCourseDto modifyCourseDto = courseModifyRequsetDto.getCourse();
        Course course = new Course(modifyCourseDto, user);
        course = courseRepository.save(course);


        //Place relation 생성
        List<ModifyCoursePlaceRelationDto> placeRelationDtos = courseModifyRequsetDto.getPlaceRelations()
                .stream()
                .sorted(Comparator.comparing(ModifyCoursePlaceRelationDto::getPlaceId))
                .collect(Collectors.toList());

        List<Place> places = placeRepository.findPlacesByIdOrderByIdAsc(
                placeRelationDtos.stream().map(ModifyCoursePlaceRelationDto::getPlaceId).collect(Collectors.toList())
        );
        List<CoursePlaceRelation> placeRelations = new ArrayList<>();

        for (int idx = 0; idx < placeRelationDtos.stream().count(); idx++){

            ModifyCoursePlaceRelationDto placeRelationDto = placeRelationDtos.get(idx);
            Place place = places.get(idx);

            CoursePlaceRelation placeRelation = CoursePlaceRelation.builder()
                    .place(place)
                    .course(course)
                    .placeOrder(placeRelationDto.getPlaceOrder())
                    .memo(placeRelationDto.getMemo())
                    .visitTime(placeRelationDto.getVisitTime())
                    .build();
            placeRelations.add(coursePlaceRelationRepository.save(placeRelation));
        }

        course.setCoursePlaceRelations(placeRelations);

        return getCourse(course.getId());
    }

    @Transactional
    public CourseDetailDto modifyCourse(Long courseId, CourseModifyRequsetDto courseModifyRequsetDto) {

        //course modify
        ModifyCourseDto modifyCourseDto = courseModifyRequsetDto.getCourse();
        Course course = courseRepository.findById(courseId).orElseThrow(); //TODO exception
        course.modifyCourse(
                modifyCourseDto.getTitle(),
                Region.findByString(modifyCourseDto.getRegion()), modifyCourseDto.getExpectedAt()
        );
        courseRepository.save(course);

        //course place relation modify
        List<ModifyCoursePlaceRelationDto> newPlaceRelations = courseModifyRequsetDto.getPlaceRelations();
        List<Long> newPlaceIds = newPlaceRelations.stream()
                .sorted(Comparator.comparing(ModifyCoursePlaceRelationDto::getPlaceOrder))
                .map(ModifyCoursePlaceRelationDto::getPlaceId)
                .collect(Collectors.toList());

        List<CoursePlaceRelation> curPlaceRelations = courseRepository.findPlaceRelationByCourseId(courseId);
        Long newPlaceCount = newPlaceRelations.stream().count();
        Long curPlaceCount = curPlaceRelations.stream().count();

        //TODO n+1 query 문제
        for (int idx = 0; idx < curPlaceCount && idx < newPlaceCount; idx++){
            ModifyCoursePlaceRelationDto updatePlaceRelation = newPlaceRelations.get(idx);
            courseRepository.updatePlaceRelations(courseId, updatePlaceRelation);
        }
        for (int idx = curPlaceCount.intValue(); idx < newPlaceCount; idx++){
            ModifyCoursePlaceRelationDto insertPlaceRelation = newPlaceRelations.get(idx);
            courseRepository.insertPlaceRelations(courseId, insertPlaceRelation);
        }
        Long deleteCount = courseRepository.deletePlaceRelations(courseId, newPlaceIds);

        return getCourse(courseId);
    }


}
