package app.hdj.datepick.domain.place.dto.request;

import app.hdj.datepick.domain.place.entity.Place;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.stereotype.Component;


@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
                .latitude(this.latitude)
                .longitude(this.longitude)
                .rating(this.rating)
                //String -> Region Enum , Exception
                .reviewCount(0L)
                .pickCount(0L)
                .build();
    }

}
