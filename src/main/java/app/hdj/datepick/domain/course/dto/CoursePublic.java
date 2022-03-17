package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.tag.dto.TagPublic;
import app.hdj.datepick.domain.user.dto.UserPublic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public class CoursePublic {

    private Long id;
    private String title;
    private LocalDateTime meetAt;
    private String imageUrl;
    private Boolean isPrivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long viewCount;
    private Long pickCount;
    private Boolean isPicked;
    private UserPublic user;
    private List<TagPublic> tags;

    public static CoursePublic from(Course course, Long userId) {
        return new CoursePublic(
                course.getId(),
                course.getTitle(),
                course.getMeetAt(),
                course.getImageUrl(),
                course.getIsPrivate(),
                course.getCreatedAt(),
                course.getUpdatedAt(),
                course.getViewCount(),
                course.getPickCount(),
                userId != null && course.getCoursePicks().stream()
                        .anyMatch(coursePick -> Objects.equals(coursePick.getUser().getId(), userId)),
                UserPublic.from(course.getUser()),
                course.getCourseTags().stream()
                        .map(courseTagRelation -> TagPublic.from(courseTagRelation.getTag()))
                        .collect(Collectors.toList())
        );
    }

}
