package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.entity.relation.CourseFeaturedRelation;
import app.hdj.datepick.global.common.entity.relation.CoursePlaceRelation;
import app.hdj.datepick.global.common.enums.Region;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "course")
public class Course extends BaseTimeEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "region", nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(name = "expected_at", nullable = false)
    private LocalDateTime expectedAt;

    @Column(name = "pick_count", nullable = false)
    @ColumnDefault("0")
    private Integer pickCount;

    @Column(name = "import_count", nullable = false)
    @ColumnDefault("0")
    private Integer importCount;

    @Column(name = "is_private", nullable = false)
    @ColumnDefault("false")
    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CoursePlaceRelation> coursePlaceRelations;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CourseFeaturedRelation> courseFeaturedRelations;

    public void modifyCourse(String title, Region region, LocalDateTime expectedAt){
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
    }
}
