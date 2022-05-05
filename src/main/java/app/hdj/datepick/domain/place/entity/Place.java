package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String kakaoId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Float rating;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long viewCount = 0L;

    @Builder
    private Place(Long id, String kakaoId, String name, Float rating, String address, Double latitude, Double longitude) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.rating = Optional.ofNullable(rating).orElse(0F);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceCategoryRelation> placeCategories = List.of();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<CoursePlaceRelation> placeCourses = List.of();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlacePick> placePicks = List.of();

    public void increaseView() {
        viewCount++;
    }
}