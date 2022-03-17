package app.hdj.datepick.domain.course.controller;

import app.hdj.datepick.domain.course.dto.CoursePublic;
import app.hdj.datepick.domain.course.dto.CourseRequest;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.service.CourseService;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.enums.CustomSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/courses")
public class CourseController {

    private final CourseService courseService;

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
    CoursePublic addCourse(@AuthenticationPrincipal Long userId, @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.addCourse(userId, courseRequest);
    }

    @GetMapping("/{courseId}")
    CoursePublic getCourse(@AuthenticationPrincipal Long userId,
                           @PathVariable Long courseId) {
        return courseService.getCourse(courseId, userId);
    }

//    @PatchMapping("/{courseId}")
//
//    @DeleteMapping("/{courseId}")
//
//    @PostMapping("/{courseId}/places")
//
//    @PatchMapping("/{courseId}/places")
//
//    @DeleteMapping("/{courseId}/places")
//
//    @PostMapping("/{courseId}/image")
//
//    @DeleteMapping("/{courseId}/image")

}
