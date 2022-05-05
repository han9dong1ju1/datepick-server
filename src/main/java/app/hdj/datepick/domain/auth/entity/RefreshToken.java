package app.hdj.datepick.domain.auth.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    @Builder
    private RefreshToken(Long id, String token, Long userId, String uuid, LocalDateTime expireAt) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.uuid = uuid;
        this.expireAt = expireAt;
    }
}
