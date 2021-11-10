package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.course.dto.request.ModifyCourseDto;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.pick.entity.CoursePick;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.common.enums.Region;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
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

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<CoursePlaceRelation> coursePlaceRelations;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<CourseFeaturedRelation> courseFeaturedRelations;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<CoursePick> coursePicks;

    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Diary diary;

    public void modifyCourse(String title, Region region, LocalDateTime expectedAt){
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
    }
    public void newCourse(ModifyCourseDto modifyCourseDto, User user){
        this.title = modifyCourseDto.getTitle();
        this.region = Region.findByString(modifyCourseDto.getRegion());
        this.expectedAt = modifyCourseDto.getExpectedAt();
        this.user = user;
        this.pickCount = 0;
        this.importCount = 0;
        this.isPrivate = false;
    }
}
