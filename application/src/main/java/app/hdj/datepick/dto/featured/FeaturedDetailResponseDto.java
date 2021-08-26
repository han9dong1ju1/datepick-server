package app.hdj.datepick.dto.featured;

import app.hdj.datepick.domain.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private String content;
    private List<Course> courses;
}
