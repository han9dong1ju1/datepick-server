package app.hdj.datepick.domain.place.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class PlaceMetaDto {

    private Long id;
    private Long kakaoId;
    private String name;
    private Float rating;
    private String address;
    private Double latitude;
    private Double longitude;

    private Category categorys;
    private Boolean isPicked;

    @Getter
    @AllArgsConstructor
    class Category{
        private String type;
        private String subtype;
        private String category;
    }

    @QueryProjection
    public PlaceMetaDto(Long id, Long kakaoId, String name, Float rating, String address, Double latitude, Double longitude, String type, String subtype, String category, Boolean isPicked) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categorys = new Category(type, subtype, category);
        this.isPicked = isPicked;
    }
}
