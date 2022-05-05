package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private LocalDateTime meetAt;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isPrivate;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long viewCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Builder
    private Course(Long id, String title, LocalDateTime meetAt, String imageUrl, Boolean isPrivate, User user) {
        this.id = id;
        this.title = title;
        this.meetAt = meetAt;
        this.imageUrl = imageUrl;
        this.isPrivate = Optional.ofNullable(isPrivate).orElse(false);
        this.user = user;
    }

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CourseTagRelation> courseTags = List.of();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CourseFeaturedRelation> courseFeatureds = List.of();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CoursePlaceRelation> coursePlaces = List.of();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CoursePick> coursePicks = List.of();

    public void increaseView() {
        viewCount++;
    }
}
