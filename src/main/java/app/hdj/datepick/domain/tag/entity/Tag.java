package app.hdj.datepick.domain.tag.entity;

import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag extends BaseEntity<Byte> {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<CourseTagRelation> tagCourses;

}
