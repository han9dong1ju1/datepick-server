package app.hdj.datepick.domain.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    private Long id;
    private Long kakaoId;
    private String name;
    private float rating;
    private String address;
    private String contact;
    private double latitude;
    private double longitude;
    //TODO Category 분리 여부 결정
    private Categorys categorys;

    private String homepage;
    private List<String> photos;
    private float region;

    //User feature
    private boolean picked;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Categorys{
        String category;
        String type;
        String subtype;
    }
    public void mapCategorys(String category, String type, String subtype){
        this.categorys = new Categorys(category, type, subtype);
    }

    public float getDistance(float curLatitude, float curLongitude){
        return 0;
    }

}
