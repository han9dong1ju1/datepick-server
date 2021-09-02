package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.global.common.entity.BaseEntity;
import app.hdj.datepick.global.common.entity.CourseFeaturedRelation;
import app.hdj.datepick.global.common.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "course")
public class Course extends BaseEntity<Long> {

    @Column(name = "title", columnDefinition = "varchar(100)", nullable = false)
    private String title;

    @Column(name = "region", nullable = false)
    private Byte region;

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

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CoursePlaceRelation> coursePlaceRelations;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<CourseFeaturedRelation> courseFeaturedRelations;

}
