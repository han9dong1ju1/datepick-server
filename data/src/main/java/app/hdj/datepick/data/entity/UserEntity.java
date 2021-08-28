package app.hdj.datepick.data.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


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

    @Column(name = "uid", columnDefinition = "varchar(128)", nullable = false, unique = true)
    private String uid;

    @Column(name = "nickname", columnDefinition = "varchar(16)", nullable = false, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @ColumnDefault("'N'")
    private Character gender;

    @Column(name = "photo_url", nullable = false)
    @ColumnDefault("''")    // TODO: 기본 프로필 사진 링크 추가
    private String profileUrl;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp createdAt;

}
