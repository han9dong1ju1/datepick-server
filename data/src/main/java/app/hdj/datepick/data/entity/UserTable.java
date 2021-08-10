package app.hdj.datepick.data.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid", columnDefinition = "varchar(128) not null unique")
    private String uid;

    @Column(name = "nickname", columnDefinition = "varchar(16) not null unique")
    private String nickname;

    @Column(name = "gender", columnDefinition = "tinyint(1) not null default 0")
    private boolean gender;

    @Column(name = "photo_url", columnDefinition = "varchar(255) not null default ''")
    private String profileUrl;

    @Column(name = "created_at", columnDefinition = "timestamp not null default now()")
    private Timestamp created_at;

}
