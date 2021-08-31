package app.hdj.datepick.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid", columnDefinition = "varchar(128) not null unique")
    private String uid;

    @Column(name = "nickname", columnDefinition = "varchar(16) not null unique")
    private String nickname;

    @Column(name = "gender", columnDefinition = "char(1) not null default 'N'")
    private Character gender;

    @Column(name = "photo_url", columnDefinition = "varchar(255) not null default ''")
    private String profileUrl;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp created_at;

}
