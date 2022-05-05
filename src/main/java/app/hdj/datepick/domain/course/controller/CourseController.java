package app.hdj.datepick.domain.course.controller;

import static app.hdj.datepick.global.util.ViewUtil.getAlreadyViewed;
import static app.hdj.datepick.global.util.ViewUtil.setViewCookie;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.dto.CourseRequest;
import app.hdj.datepick.domain.course.dto.CourseResponse;
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
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    CustomPage<CourseResponse> getCoursePage(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick",
            "popular"}) String sort,
        @Valid CourseFilterParam courseFilterParam
    ) {
        return courseService.getPublicCoursePage(pagingParam, CustomSort.from(sort),
                                                 courseFilterParam, userId);
    }

    @Authorize({Role.USER})
    @GetMapping("me")
    CustomPage<CourseResponse> getMyCoursePage(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick",
            "popular"}) String sort,
        @Valid CourseFilterParam courseFilterParam
    ) {
        courseFilterParam.setUserId(userId);
        return courseService.getCoursePage(pagingParam, CustomSort.from(sort), courseFilterParam,
                                           userId);
    }

    @Authorize({Role.USER})
    @GetMapping("picked")
    CustomPage<CourseResponse> getPickedCoursePage(
        @AuthPrincipal Long userId,
        @Valid PagingParam pagingParam,
        @ValueOfEnum(enumClass = CustomSort.class, acceptedValues = {"latest", "pick",
            "popular"}) String sort,
        @Valid CourseFilterParam courseFilterParam
    ) {
        return courseService.getPickedCoursePage(pagingParam, CustomSort.from(sort),
                                                 courseFilterParam, userId);
    }

    @Authorize({Role.USER})
    @PostMapping("")
    CourseResponse addCourse(
        @AuthPrincipal Long userId, @Valid @RequestBody CourseRequest courseRequest
    ) {
        return courseService.addCourse(courseRequest, userId);
    }

    @GetMapping("/{courseId}")
    CourseResponse getCourse(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        HttpServletResponse response,
        @CookieValue(name = "course_view", required = false, defaultValue = "/") String cookieValue
    ) {
        boolean alreadyViewed = getAlreadyViewed(cookieValue, courseId);
        setViewCookie(alreadyViewed, "course_view", cookieValue, courseId, response);
        return courseService.getCourse(courseId, userId, alreadyViewed);
    }

    @Authorize({Role.USER})
    @PatchMapping("/{courseId}")
    CourseResponse modifyCourse(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        @Valid @RequestBody CourseRequest courseRequest
    ) {
        return courseService.modifyCourse(courseId, courseRequest, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}")
    void removeCourse(
        @AuthPrincipal Long userId, @PathVariable Long courseId
    ) {
        courseService.removeCourse(courseId, userId);
    }

    @GetMapping("/{courseId}/places")
    List<CoursePlacePublic> getCoursePlaces(@PathVariable Long courseId) {
        return coursePlaceService.getCoursePlaces(courseId);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/places")
    List<CoursePlacePublic> addCoursePlaces(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        @Valid @RequestBody List<@Positive Long> placeIds
    ) {
        return coursePlaceService.addCoursePlaces(userId, courseId, placeIds);
    }

    @Authorize({Role.USER})
    @PatchMapping("/{courseId}/places")
    List<CoursePlacePublic> modifyCoursePlaces(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        @Valid @RequestBody List<@Positive Long> coursePlaceIds
    ) {
        return coursePlaceService.modifyCoursePlacesOrder(userId, courseId, coursePlaceIds);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/places")
    List<CoursePlacePublic> removeCoursePlaces(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        @Valid @RequestBody List<@Positive Long> coursePlaceIds
    ) {
        return coursePlaceService.removeCoursePlaces(userId, courseId, coursePlaceIds);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/image")
    ImageUrl addCourseImage(
        @AuthPrincipal Long userId,
        @PathVariable Long courseId,
        @NotNull @ImageFile @ModelAttribute MultipartFile image
    ) {
        return courseService.addCourseImage(courseId, image, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/image")
    void removeCourseImage(
        @AuthPrincipal Long userId, @PathVariable Long courseId
    ) {
        courseService.removeCourseImage(userId, courseId);
    }

    @Authorize({Role.USER})
    @PostMapping("/{courseId}/pick")
    void addCoursePick(
        @AuthPrincipal Long userId, @PathVariable Long courseId
    ) {
        coursePickService.addCoursePick(courseId, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("/{courseId}/pick")
    void removeCoursePick(
        @AuthPrincipal Long userId, @PathVariable Long courseId
    ) {
        coursePickService.removeCoursePick(courseId, userId);
    }
}
