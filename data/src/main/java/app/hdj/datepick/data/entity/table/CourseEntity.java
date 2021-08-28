package app.hdj.datepick.data.entity.table;

import app.hdj.datepick.data.entity.BaseTimeEntity;
import app.hdj.datepick.data.entity.relation.CourseFeaturedRelationEntity;
import app.hdj.datepick.data.entity.relation.CoursePlaceRelationEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseEntity extends BaseTimeEntity<Long> {

    @Column(name = "title", columnDefinition = "varchar(50) not null")
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

    @ManyToOne(cascade = CascadeType.ALL)   // TODO: CascadeType 수정
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)   // TODO: CascadeType 수정
    List<CoursePlaceRelationEntity> coursePlaceRelations;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)   // TODO: CascadeType 수정
    List<CourseFeaturedRelationEntity> courseFeaturedRelations;
    
}
