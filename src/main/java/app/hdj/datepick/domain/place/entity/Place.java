package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.global.common.entity.BaseEntity;
import app.hdj.datepick.global.common.entity.BaseTimeEntity;
import app.hdj.datepick.global.common.entity.relation.CoursePlaceRelation;
import app.hdj.datepick.global.common.enums.Region;
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
public class Place extends BaseEntity<Long> {

    //TODO kakao id 타입 결정
    @Column(name = "kakao_id", unique = true, nullable = false)
    private String kakaoId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "rating", nullable = false)
    @ColumnDefault("0")
    private Float rating;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "subtype", nullable = false)
    private String subtype;

    @Column(name = "category", nullable = false)
    private String category;

    //TODO 제거요망
    @Column(name = "homepage", nullable = false)
    private String homepage;

    @Column(name = "review_count", nullable = false)
    @ColumnDefault("0")
    private Integer reviewCount;

    @Column(name = "blog_review_count", nullable = false)
    @ColumnDefault("0")
    private Integer blogReviewCount;

    //TODO Enum String 생성
    @Column(name = "region", nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(name = "pick_count", nullable = false)
    @ColumnDefault("0")
    private Integer pickCount;

/*고민한 양방향 관계 문제점 : 하나의 장소는 여러개의 course에 입력가능함.
따라서 course의 places를 찾을때, list 에서 course Id에 맞는 course Relation을 찾는 과정이 필수적임.
그래서 굳이 양방향 관계를 만들기보다, Relation테이블에서 직접적으로 course id를 탐색한다.
 */
//    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<CoursePlaceRelation> coursePlaceRelations;

//    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY) //cascade = CascadeType.ALL
//    List<PlacePick> placePicks;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceReview> placeReviews;

}