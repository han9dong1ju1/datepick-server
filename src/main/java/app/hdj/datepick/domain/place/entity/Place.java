package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "place")
public class Place extends BaseTimeEntity<Long> {

    //TODO kakao id 타입 결정
    @Column(name = "kakao_id", columnDefinition = "bigint", nullable = false)
    private Long kakaoId;

    @Column(name = "name", columnDefinition = "varchar(200)", nullable = false)
    private String name;

    @Column(name = "rating", columnDefinition = "float", nullable = false)
    @ColumnDefault("0")
    private Float rating;

    @Column(name = "address", columnDefinition = "varchar(200)", nullable = false)
    private String address;

    @Column(name = "latitude", columnDefinition = "double", nullable = false)
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "double not null")
    private Double longitude;

    @Column(name = "contact", columnDefinition = "varchar(30)", nullable = false)
    private String contact;

    @Column(name = "type", columnDefinition = "varchar(30)", nullable = false)
    private String type;

    @Column(name = "subtype", columnDefinition = "varchar(30)", nullable = false)
    private String subtype;

    @Column(name = "category", columnDefinition = "varchar(50)", nullable = false)
    private String category;

    @Column(name = "homepage", columnDefinition = "varchar(200)", nullable = false)
    private String homepage;

    @Column(name = "review_count", columnDefinition = "int", nullable = false)
    @ColumnDefault("0")
    private Integer reviewCount;

    @Column(name = "blog_review_count", columnDefinition = "int", nullable = false)
    @ColumnDefault("0")
    private Integer blogReviewCount;

    //TODO Enum String 생성
    @Column(name = "region", columnDefinition = "varchar(20)", nullable = false)
    private String region;

    @Column(name = "pick_count", columnDefinition = "int", nullable = false)
    @ColumnDefault("0")
    private Integer pickCount;

//    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<CoursePlaceRelation> coursePlaceRelations;

//    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY) //cascade = CascadeType.ALL
//    List<PlacePick> placePicks;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceReview> placeReviews;

}