package app.hdj.datepick.domain.place.dto.request;

import app.hdj.datepick.domain.place.entity.Place;
import app.hdj.datepick.global.common.enums.Region;
import lombok.*;
import org.springframework.stereotype.Component;


@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PlaceRequestDto {//Post, Patch 에 사용

    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private String contact;
    private Double latitude;
    private Double longitude;

    //500 발생 가능성 validation 적용
    //private Region region;
    private String region;

    private String type;
    private String subtype;
    private String category;

    public Place toPlace(){
        return Place.builder()
                .kakaoId(this.kakaoId)
                .name(this.name)
                .address(this.address)
                .contact(this.contact)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .rating(this.rating)
                .type(this.type)
                .subtype(this.subtype)
                .category(this.category)
                //String -> Region Enum , Exception
                .region(Region.findByString(this.region))
                .homepage("")
                .blogReviewCount(0)
                .reviewCount(0)
                .pickCount(0)
                .build();
    }

}
