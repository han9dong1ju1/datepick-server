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
    private Category category;
    private String homepage;
    private List<String> photos;
    private float region;

    //User feature
    private boolean picked;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Category{
        String type;
        String subtype;
    }
    public void mapCategory(String type, String subtype){
        this.category = new Category(type, subtype);
    }
}
