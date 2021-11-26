package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
import app.hdj.datepick.global.common.enums.Gender;
import app.hdj.datepick.global.common.enums.Provider;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseAllTimeEntity<Long> {

    @Column(columnDefinition = "varchar(128)", nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(columnDefinition = "varchar(16)", nullable = false)
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("'U'")
    private Gender gender;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isBanned;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Column
    private String profileImage;

}
