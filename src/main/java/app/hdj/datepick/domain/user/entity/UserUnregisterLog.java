package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserUnregisterLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String reason;

    @Column
    private String content;

    @Column
    private LocalDateTime expireAt;

    @Builder
    private UserUnregisterLog(User user, String reason, String content, LocalDateTime expireAt) {
        this.user = user;
        this.reason = reason;
        this.content = content;
        this.expireAt = expireAt;
    }
}
