package app.hdj.datepick.domain.course.controller;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.dto.CoursePublic;
import app.hdj.datepick.domain.course.dto.CourseRequest;
import app.hdj.datepick.domain.course.service.CoursePickService;
import app.hdj.datepick.domain.course.service.CoursePlaceService;
import app.hdj.datepick.domain.course.service.CourseService;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/courses")
public class CourseController {

    private final CourseService courseService;
    private final CoursePlaceService coursePlaceService;
    private final CoursePickService coursePickService;

    @GetMapping("")
    CustomPage<CoursePublic> getCoursePage(@AuthenticationPrincipal Long userId,
                                           @Valid PagingParam pagingParam,
                                           @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                           @Valid CourseFilterParam courseFilterParam) {
        return courseService.getPublicCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @GetMapping("me")
    CustomPage<CoursePublic> getMyCoursePage(@AuthenticationPrincipal Long userId,
                                             @Valid PagingParam pagingParam,
                                             @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                             @Valid CourseFilterParam courseFilterParam) {
        courseFilterParam.setUserId(userId);
        return courseService.getCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @GetMapping("picked")
    CustomPage<CoursePublic> getPickedCoursePage(@AuthenticationPrincipal Long userId,
                                                 @Valid PagingParam pagingParam,
                                                 @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                                 @Valid CourseFilterParam courseFilterParam) {
        return courseService.getPickedCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @PostMapping("")
    CoursePublic addCourse(@AuthenticationPrincipal Long userId,
                           @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.addCourse(courseRequest, userId);
    }

    @GetMapping("/{courseId}")
    CoursePublic getCourse(@AuthenticationPrincipal Long userId,
                           @PathVariable Long courseId) {
        return courseService.getCourse(courseId, userId);
    }

    @PatchMapping("/{courseId}")
    CoursePublic modifyCourse(@AuthenticationPrincipal Long userId,
                              @PathVariable Long courseId,
                              @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.modifyCourse(courseId, courseRequest, userId);
    }

    @DeleteMapping("/{courseId}")
    void removeCourse(@AuthenticationPrincipal Long userId,
                      @PathVariable Long courseId) {
        courseService.removeCourse(courseId, userId);
    }

    @PostMapping("/{courseId}/places")
    List<CoursePlaceRelation> addCoursePlaces(@AuthenticationPrincipal Long userId,
                                              @PathVariable Long courseId,
                                              @Valid @RequestBody List<@Positive Long> placeIds) {
        return null;
    }

    @PatchMapping("/{courseId}/places")
    List<Long> modifyCoursePlaces(@AuthenticationPrincipal Long userId,
                                  @PathVariable Long courseId,
                                  @Valid @RequestBody List<@Positive Long> placeIds) {
        return placeIds;
    }


    @DeleteMapping("/{courseId}/places")
    List<Long> deleteCoursePlaces(@AuthenticationPrincipal Long userId,
                                  @PathVariable Long courseId,
                                  @Valid @RequestBody List<@Positive Long> placeIds) {
        return placeIds;
    }

    @PostMapping("/{courseId}/image")
    ImageUrl addCourseImage(@AuthenticationPrincipal Long userId,
                            @PathVariable Long courseId,
                            @NotNull @ImageFile @ModelAttribute MultipartFile image) {
        return courseService.addCourseImage(courseId, image, userId);
    }


    @DeleteMapping("/{courseId}/image")
    void removeCourseImage(@AuthenticationPrincipal Long userId,
                           @PathVariable Long courseId) {
        courseService.removeCourseImage(userId, courseId);
    }

    @PostMapping("/{courseId}/pick")
    void addCoursePick(@AuthenticationPrincipal Long userId,
                       @PathVariable Long courseId) {
        coursePickService.addCoursePick(courseId, userId);
    }

    @DeleteMapping("/{courseId}/pick")
    void removeCoursePick(@AuthenticationPrincipal Long userId,
                       @PathVariable Long courseId) {
        coursePickService.removeCoursePick(courseId, userId);
    }
}
