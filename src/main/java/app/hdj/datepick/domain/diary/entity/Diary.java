package app.hdj.datepick.domain.diary.entity;


import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "diary")
public class Diary extends BaseTimeEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", columnDefinition = "varchar(30)", nullable = false)
    private String title;

    @Column(name = "like_count", columnDefinition = "int", nullable = false)
    @ColumnDefault("0")
    private Integer likeCount;

    @Column(name = "style", columnDefinition = "tiny(4)", nullable = false)
    @ColumnDefault("0")
    private Byte style;  //TODO style enum 생성?


}
