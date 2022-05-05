package app.hdj.datepick.domain.course.dto;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.tag.dto.TagSimpleResponse;
import app.hdj.datepick.domain.user.dto.UserPublic;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseResponse {

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
    private List<TagSimpleResponse> tags;

    public static CourseResponse from(Course course, Long userId) {
        final Comparator<CourseTagRelation> comp = Comparator.comparingInt(
            ctr -> ctr.getTag().getId());
        return new CourseResponse(course.getId(), course.getTitle(), course.getMeetAt(),
                                  course.getImageUrl(), course.getIsPrivate(),
                                  course.getCreatedAt(), course.getUpdatedAt(),
                                  course.getViewCount(), (long) course.getCoursePicks().size(),
                                  userId != null && course.getCoursePicks().stream().anyMatch(
                                      coursePick -> coursePick.getUser().getId().equals(userId)),
                                  UserPublic.from(course.getUser()),
                                  course.getCourseTags().stream().sorted(comp).map(
                                          courseTagRelation -> TagSimpleResponse.from(
                                              courseTagRelation.getTag()))
                                      .collect(Collectors.toList()));
    }
}
