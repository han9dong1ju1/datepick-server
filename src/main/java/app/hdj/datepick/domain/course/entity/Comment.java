package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.diary.entity.Diary;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseAllTimeEntity<Long> {

    @Column(nullable = false)
    String content;

    @Column
    Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id",nullable = false)
    private Diary diary;

}
