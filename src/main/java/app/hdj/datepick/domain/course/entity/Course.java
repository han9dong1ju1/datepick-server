package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import app.hdj.datepick.domain.relation.entity.CoursePick;
import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.entity.CourseTagRelation;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course extends BaseTimeEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

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
    private Long viewCount;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CourseTagRelation> courseTags;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CourseFeaturedRelation> courseFeatureds;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CoursePlaceRelation> coursePlaces;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CoursePick> coursePicks;

    public void increaseView() {
        viewCount++;
    }

}
