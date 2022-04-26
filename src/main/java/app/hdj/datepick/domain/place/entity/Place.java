package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import app.hdj.datepick.domain.relation.entity.PlaceCategoryRelation;
import app.hdj.datepick.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

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
    private Long viewCount;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceCategoryRelation> placeCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<CoursePlaceRelation> placeCourses;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlacePick> placePicks;

}