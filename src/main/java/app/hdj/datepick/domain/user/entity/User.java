package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.enums.Gender;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "user")
public class User extends BaseTimeEntity<Long> {

    @Column(name = "uid", columnDefinition = "varchar(128)", nullable = false, unique = true)
    private String uid;

    @Column(name = "nickname", columnDefinition = "varchar(16)", nullable = false)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @ColumnDefault("U")
    private Gender gender;

    @Column(name = "photo_url", nullable = false)
    @ColumnDefault("''")    // TODO: 기본 프로필 사진 링크 추가
    private String profileUrl;

}
