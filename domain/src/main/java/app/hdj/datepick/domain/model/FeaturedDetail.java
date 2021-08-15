package app.hdj.datepick.domain.model;

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
    private FeaturedMeta meta;
    private String content;
    private List<Course> courses;
}
