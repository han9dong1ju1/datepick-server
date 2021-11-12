package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.enums.Gender;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "profile_image")
    private String profileImage;

}
