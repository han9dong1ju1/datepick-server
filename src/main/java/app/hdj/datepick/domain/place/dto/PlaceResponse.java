package app.hdj.datepick.domain.place.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PlaceResponse {

    private Long id;
    private String kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private List<CategoryDto> categories;

    private Boolean isPicked;
    private Long viewCount;
    private Long reviewCount;
    private Long pickCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
