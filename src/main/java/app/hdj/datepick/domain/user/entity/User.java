package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.common.entity.BaseEntity;
import app.hdj.datepick.domain.place.entity.PlacePick;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
public class User extends BaseTimeEntity<Long> {

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

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<PlacePick> placePicks;

}
