package app.hdj.datepick.domain.course.entity;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long pickCount;

}
