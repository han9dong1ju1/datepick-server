package app.hdj.datepick.domain.user.entity;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

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

    @Builder
    private User(
        Long id,
        String uid,
        Provider provider,
        String nickname,
        String email,
        Gender gender,
        Boolean isBanned,
        Boolean isDeleted,
        String imageUrl
    ) {
        this.id = id;
        this.uid = uid;
        this.provider = provider;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.isBanned = Optional.ofNullable(isBanned).orElse(false);
        this.isDeleted = Optional.ofNullable(isDeleted).orElse(false);
        this.imageUrl = imageUrl;
    }
}
