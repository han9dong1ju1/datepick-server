package app.hdj.datepick.domain.place.entity;

import app.hdj.datepick.domain.place.dto.request.PlaceRequestDto;
import app.hdj.datepick.domain.review.entity.PlaceReview;
import app.hdj.datepick.global.common.entity.BaseAllTimeEntity;
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
@Entity
public class Place extends BaseAllTimeEntity<Long> {

    @Column(unique = true)
    private String kakaoId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private Float rating;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column
    private String contact;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String subtype;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long reviewCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long blogReviewCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long pickCount;

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


    public PlaceRequestDto toPlaceRequestDto(){
        return PlaceRequestDto.builder()
                .kakaoId(this.kakaoId)
                .name(this.name)
                .rating(this.rating)
                .address(this.address)
                .contact(this.contact)
                .latitude(this.latitude)
                .longitude(this.longitude)

                .region(this.region.toString())
                .type(this.type)
                .subtype(this.subtype)
                .category(this.category)
                .build();
    }

}