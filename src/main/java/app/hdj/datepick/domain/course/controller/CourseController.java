package app.hdj.datepick.domain.course.controller;

import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.dto.CoursePublic;
import app.hdj.datepick.domain.course.dto.CourseRequest;
import app.hdj.datepick.domain.course.service.CoursePickService;
import app.hdj.datepick.domain.course.service.CoursePlaceService;
import app.hdj.datepick.domain.course.service.CourseService;
import app.hdj.datepick.domain.relation.dto.CoursePlacePublic;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.annotation.ImageFile;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
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
    CustomPage<CoursePublic> getCoursePage(@AuthPrincipal Long userId,
                                           @Valid PagingParam pagingParam,
                                           @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                           @Valid CourseFilterParam courseFilterParam) {
        return courseService.getPublicCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @Authorize({Role.USER})
    @GetMapping("me")
    CustomPage<CoursePublic> getMyCoursePage(@AuthPrincipal Long userId,
                                             @Valid PagingParam pagingParam,
                                             @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                             @Valid CourseFilterParam courseFilterParam) {
        courseFilterParam.setUserId(userId);
        return courseService.getCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @Authorize({Role.USER})
    @GetMapping("picked")
    CustomPage<CoursePublic> getPickedCoursePage(@AuthPrincipal Long userId,
                                                 @Valid PagingParam pagingParam,
                                                 @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick", "popular"}) String sort,
                                                 @Valid CourseFilterParam courseFilterParam) {
        return courseService.getPickedCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam, userId);
    }

    @Authorize({Role.USER})
    @PostMapping("")
    CoursePublic addCourse(@AuthPrincipal Long userId,
                           @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.addCourse(courseRequest, userId);
    }

    @GetMapping("/{courseId}")
    CoursePublic getCourse(@AuthPrincipal Long userId,
                           @PathVariable Long courseId) {
        return courseService.getCourse(courseId, userId);
    }

    @Authorize({Role.USER})
    @PatchMapping("/{courseId}")
    CoursePublic modifyCourse(@AuthPrincipal Long userId,
                              @PathVariable Long courseId,
                              @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.modifyCourse(courseId, courseRequest, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}")
    void removeCourse(@AuthPrincipal Long userId,
                      @PathVariable Long courseId) {
        courseService.removeCourse(courseId, userId);
    }

    @GetMapping("/{courseId}/places")
    List<CoursePlacePublic> getCoursePlaces(@PathVariable Long courseId) {
        return coursePlaceService.getCoursePlaces(courseId);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/places")
    List<CoursePlacePublic> addCoursePlaces(@AuthPrincipal Long userId,
                                              @PathVariable Long courseId,
                                              @Valid @RequestBody List<@Positive Long> placeIds) {
        return coursePlaceService.addCoursePlaces(userId, courseId, placeIds);
    }

    @Authorize({Role.USER})
    @PatchMapping("/{courseId}/places")
    List<CoursePlacePublic> modifyCoursePlaces(@AuthPrincipal Long userId,
                                  @PathVariable Long courseId,
                                  @Valid @RequestBody List<@Positive Long> coursePlaceIds) {
        return coursePlaceService.modifyCoursePlacesOrder(userId, courseId, coursePlaceIds);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/places")
    List<CoursePlacePublic> removeCoursePlaces(@AuthPrincipal Long userId,
                                  @PathVariable Long courseId,
                                  @Valid @RequestBody List<@Positive Long> coursePlaceIds) {
        return coursePlaceService.removeCoursePlaces(userId, courseId, coursePlaceIds);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/image")
    ImageUrl addCourseImage(@AuthPrincipal Long userId,
                            @PathVariable Long courseId,
                            @NotNull @ImageFile @ModelAttribute MultipartFile image) {
        return courseService.addCourseImage(courseId, image, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/image")
    void removeCourseImage(@AuthPrincipal Long userId,
                           @PathVariable Long courseId) {
        courseService.removeCourseImage(userId, courseId);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/pick")
    void addCoursePick(@AuthPrincipal Long userId,
                       @PathVariable Long courseId) {
        coursePickService.addCoursePick(courseId, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/pick")
    void removeCoursePick(@AuthPrincipal Long userId,
                          @PathVariable Long courseId) {
        coursePickService.removeCoursePick(courseId, userId);
    }
}
