package app.hdj.datepick.dto.featured;

import app.hdj.datepick.domain.model.Course;
import lombok.*;

import java.util.List;

@Data
public class FeaturedDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private String content;
    private List<Course> courses;
}
