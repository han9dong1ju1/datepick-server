package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserUnregisterLog extends BaseTimeEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String reason;

    @Column
    private String content;

    @Column
    private LocalDateTime expireAt;

}
