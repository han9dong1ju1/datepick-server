package app.hdj.datepick.domain.place.dto.request;

import lombok.*;
import org.springframework.stereotype.Component;


@ToString
@Getter
@Setter
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

    private String type;
    private String subtype;
    private String category;

}
