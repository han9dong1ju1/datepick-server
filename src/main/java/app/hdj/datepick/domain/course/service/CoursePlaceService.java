package app.hdj.datepick.domain.course.service;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.domain.relation.dto.CoursePlacePublic;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.repository.CoursePlaceRepository;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoursePlaceService {

    private final CoursePlaceRepository coursePlaceRepository;
    private final CourseRepository courseRepository;

    public List<CoursePlacePublic> getCoursePlaces(Long courseId) {
        List<CoursePlaceRelation> coursePlaceRelations = coursePlaceRepository.findByCourseId(
            courseId);
        coursePlaceRelations.sort(Comparator.comparingInt(CoursePlaceRelation::getPlaceOrder));
        return coursePlaceRelations.stream().map(CoursePlacePublic::from)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CoursePlacePublic> addCoursePlaces(
        Long userId, Long courseId, List<Long> placeIds
    ) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        List<CoursePlaceRelation> coursePlaces = coursePlaceRepository.findByCourseId(courseId);
        coursePlaces.sort(Comparator.comparingInt(CoursePlaceRelation::getPlaceOrder));
        Byte maxOrder =
            coursePlaces.size() > 0 ? coursePlaces.get(coursePlaces.size() - 1).getPlaceOrder()
                : -1;
        for (Long placeId : placeIds) {
            Place place = Place.builder().id(placeId).build();
            maxOrder++;
            CoursePlaceRelation newCoursePlace = CoursePlaceRelation.builder().course(course)
                .place(place).placeOrder(maxOrder).build();
            newCoursePlace = coursePlaceRepository.save(newCoursePlace);
            coursePlaces.add(newCoursePlace);
        }
        return coursePlaces.stream().map(CoursePlacePublic::from).collect(Collectors.toList());
    }

    @Transactional
    public List<CoursePlacePublic> modifyCoursePlacesOrder(
        Long userId, Long courseId, List<Long> coursePlaceIds
    ) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        List<CoursePlaceRelation> coursePlaces = coursePlaceRepository.findByCourseId(courseId);
        for (CoursePlaceRelation coursePlace : coursePlaces) {
            int index = coursePlaceIds.indexOf(coursePlace.getId());
            if (index == -1) {
                throw new CustomException(ErrorCode.INPUT_VALUE_INVALID);
            }
            coursePlace.setPlaceOrder((byte) index);
        }
        coursePlaces.sort(Comparator.comparingInt(CoursePlaceRelation::getPlaceOrder));
        return coursePlaces.stream().map(CoursePlacePublic::from).collect(Collectors.toList());
    }

    @Transactional
    public List<CoursePlacePublic> removeCoursePlaces(
        Long userId, Long courseId, List<Long> coursePlaceIds
    ) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        coursePlaceRepository.deleteAllByIdInBatch(coursePlaceIds);
        List<CoursePlaceRelation> coursePlaces = coursePlaceRepository.findByCourseId(courseId);
        coursePlaces.sort(Comparator.comparingInt(CoursePlaceRelation::getPlaceOrder));
        int bound = coursePlaces.size();
        for (int i = 0; i < bound; i++) {
            coursePlaces.get(i).setPlaceOrder((byte) i);
        }
        return coursePlaces.stream().map(CoursePlacePublic::from).collect(Collectors.toList());
    }
}
