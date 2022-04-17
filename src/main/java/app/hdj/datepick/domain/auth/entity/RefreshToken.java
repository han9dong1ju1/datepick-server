package app.hdj.datepick.domain.auth.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken extends BaseTimeEntity<Long> {

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private LocalDateTime expireAt;

}
