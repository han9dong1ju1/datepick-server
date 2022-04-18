package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.global.entity.BaseTimeEntity;
import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.enums.Provider;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity<Long> {

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(columnDefinition = "varchar(16)", nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "char")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isBanned;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Column
    private String imageUrl;

}
