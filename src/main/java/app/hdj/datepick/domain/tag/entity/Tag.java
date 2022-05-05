package app.hdj.datepick.domain.tag.entity;

import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<CourseTagRelation> tagCourses = List.of();

    @Builder
    private Tag(String name) {
        this.name = name;
    }
}
