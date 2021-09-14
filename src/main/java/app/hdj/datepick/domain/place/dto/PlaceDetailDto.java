package app.hdj.datepick.domain.place.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PlaceDetailDto {

    private Long id;
    private Long kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private Category categorys;

    private Boolean isPicked;

    private List<String> photoUrls;

    @Getter
    @AllArgsConstructor
    class Category{
        private String type;
        private String subtype;
        private String category;
    }

    @QueryProjection
    public PlaceDetailDto(Long id, Long kakaoId, String name, Float rating, String address, Double latitude, Double longitude, String type, String subtype, String category, Boolean isPicked, List<String> photoUrls) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categorys = new Category(type, subtype, category);
        this.isPicked = isPicked;
        this.photoUrls = photoUrls;
    }
}
