package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Place extends BaseTimeEntity<Long> {

    @Column(unique = true)
    private String kakaoId;

    @Column(nullable = false)
    private String name;

    @Column
    private Float rating;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long viewCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long reviewCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long pickCount;

}