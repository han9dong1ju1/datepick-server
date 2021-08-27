package app.hdj.datepick.domain.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"courses"})
public class Featured {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String photoUrl;
    private Timestamp createdAt;
    private List<Course> courses;
}
