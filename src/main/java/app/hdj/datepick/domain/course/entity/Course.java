package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.course.dto.request.ModifyCourseDto;
import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.pick.entity.CoursePick;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import app.hdj.datepick.global.relation.entity.CourseFeaturedRelation;
import app.hdj.datepick.global.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.global.common.enums.Region;
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
@Entity
public class Course extends BaseAllTimeEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column
    private LocalDateTime expectedAt;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long pickCount;

    @Column(nullable = false)
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

    public void modifyCourse(String title, Region region, LocalDateTime expectedAt) {
        this.title = title;
        this.region = region;
        this.expectedAt = expectedAt;
    }

    public Course(ModifyCourseDto modifyCourseDto, User user) {
        this.title = modifyCourseDto.getTitle();
        this.region = Region.findByString(modifyCourseDto.getRegion());
        this.expectedAt = modifyCourseDto.getExpectedAt();
        this.user = user;
    }

}
