package app.hdj.datepick.domain.comment.entity;

import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Builder
    private Comment(Long id, String content, User user, Course course, Comment parent) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.course = course;
        this.parent = parent;
    }
}
