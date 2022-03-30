package app.hdj.datepick.domain.course.service;

import app.hdj.datepick.domain.course.dto.CourseFilterParam;
import app.hdj.datepick.domain.course.dto.CoursePublic;
import app.hdj.datepick.domain.course.dto.CourseRequest;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.course.repository.CourseRepository;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.relation.repository.CourseTagRepository;
import app.hdj.datepick.domain.tag.entity.Tag;
import app.hdj.datepick.domain.tag.repository.TagRepository;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.enums.CustomSort;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseTagRepository courseTagRepository;
    private final TagRepository tagRepository;
    private final FileService fileService;

    @PersistenceContext
    private EntityManager em;

    public CustomPage<CoursePublic> getPublicCoursePage(PagingParam pagingParam,
                                                        CustomSort customSort,
                                                        CourseFilterParam courseFilterParam,
                                                        Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Course> coursePage = courseRepository.findPublicCoursePage(courseFilterParam, pagingParam, sort);

        return new CustomPage<>(
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                coursePage.getNumber(),
                coursePage.getContent().stream()
                        .map(course -> CoursePublic.from(course, userId))
                        .collect(Collectors.toList())
        );
    }

    public CustomPage<CoursePublic> getCoursePage(PagingParam pagingParam,
                                                  CustomSort customSort,
                                                  CourseFilterParam courseFilterParam,
                                                  Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Course> coursePage = courseRepository.findCoursePage(courseFilterParam, pagingParam, sort);

        return new CustomPage<>(
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                coursePage.getNumber(),
                coursePage.getContent().stream()
                        .map(course -> CoursePublic.from(course, userId))
                        .collect(Collectors.toList())
        );
    }

    public CustomPage<CoursePublic> getPickedCoursePage(PagingParam pagingParam, CustomSort customSort, CourseFilterParam courseFilterParam, Long userId) {
        Sort sort = CustomSort.toSort(customSort, CustomSort.LATEST);
        Page<Course> coursePage = courseRepository.findPickedCoursePage(courseFilterParam, pagingParam, sort, userId);

        return new CustomPage<>(
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                coursePage.getNumber(),
                coursePage.getContent().stream()
                        .map(course -> CoursePublic.from(course, userId))
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public CoursePublic addCourse(CourseRequest courseRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Course course = Course.builder()
                .user(user)
                .title(courseRequest.getTitle())
                .meetAt(courseRequest.getMeetAt())
                .isPrivate(courseRequest.getIsPrivate())
                .build();
        course = courseRepository.save(course);
        em.refresh(course);

        return CoursePublic.from(course, userId);
    }

    public CoursePublic getCourse(Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (!course.getUser().getId().equals(userId) && course.getIsPrivate()) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        return CoursePublic.from(course, userId);
    }

    @Transactional
    public CoursePublic modifyCourse(Long courseId, CourseRequest courseRequest, Long userId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        course.setTitle(courseRequest.getTitle());
        course.setMeetAt(courseRequest.getMeetAt());
        course.setIsPrivate(courseRequest.getIsPrivate());

        List<CourseTagRelation> courseTags = course.getCourseTags();
        List<CourseTagRelation> removeCourseTags = new CopyOnWriteArrayList<>(courseTags);
        List<Byte> newTagIds = courseRequest.getTagIds();
        for (CourseTagRelation courseTag : removeCourseTags) {
            Byte tagId = courseTag.getTag().getId();
            if (newTagIds.contains(tagId)) {
                removeCourseTags.remove(courseTag);
                newTagIds.remove(tagId);
            }
        }
        courseTagRepository.deleteAllInBatch(removeCourseTags);
        courseTags.removeIf(removeCourseTags::contains);

        List<CourseTagRelation> newCourseTags = new ArrayList<>();
        List<Tag> tags = tagRepository.findAllById(newTagIds);
        for (Tag tag : tags) {
            CourseTagRelation courseTag = CourseTagRelation.builder().course(course).tag(tag).build();
            newCourseTags.add(courseTag);
        }
        courseTagRepository.saveAll(newCourseTags);
        course.setCourseTags(Stream.concat(courseTags.stream(), newCourseTags.stream()).collect(Collectors.toList()));

        return CoursePublic.from(course, userId);
    }

    public void removeCourse(Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        courseRepository.delete(course);
    }

    @Transactional
    public ImageUrl addCourseImage(Long courseId, MultipartFile image, Long userId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        String imageUrl = course.getImageUrl();
        if (imageUrl != null) {
            throw new CustomException(ErrorCode.FILE_ALREADY_EXISTS);
        }

        imageUrl = fileService.add(image, "course/" + courseId);
        course.setImageUrl(imageUrl);

        return new ImageUrl(imageUrl);
    }

    @Transactional
    public void removeCourseImage(Long userId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (!course.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        String imageUrl = course.getImageUrl();
        if (imageUrl == null) {
            throw new CustomException(ErrorCode.FILE_NOT_EXISTS);
        }

        course.setImageUrl(null);
        fileService.remove(imageUrl);
    }
}
