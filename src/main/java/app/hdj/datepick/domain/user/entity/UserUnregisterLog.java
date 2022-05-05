package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
