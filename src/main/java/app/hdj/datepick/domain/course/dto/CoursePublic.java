package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.tag.dto.TagPublic;
import app.hdj.datepick.domain.user.dto.UserPublic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        final Comparator<CourseTagRelation> comp = Comparator.comparingInt(ctr -> ctr.getTag().getId());
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
                        .anyMatch(coursePick -> coursePick.getUser().getId().equals(userId)),
                UserPublic.from(course.getUser()),
                course.getCourseTags().stream()
                        .sorted(comp)
                        .map(courseTagRelation -> TagPublic.from(courseTagRelation.getTag()))
                        .collect(Collectors.toList())
        );
    }

}
