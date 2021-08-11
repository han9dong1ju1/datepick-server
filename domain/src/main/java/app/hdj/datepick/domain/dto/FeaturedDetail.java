package app.hdj.datepick.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedDetail {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String photoUrl;
    private List<Course> courses;
}
