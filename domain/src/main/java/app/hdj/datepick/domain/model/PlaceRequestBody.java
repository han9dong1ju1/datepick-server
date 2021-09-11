package app.hdj.datepick.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRequestBody {

    private Long id;
    private Long kakaoId;
    private String name;
    private float rating;
    private String address;
    private String contact;
    private double latitude;
    private double longitude;
    private String category;
    private String type;
    private String subtype;
    private String homepage;
    private float region;
    private List<String> photos;

}
