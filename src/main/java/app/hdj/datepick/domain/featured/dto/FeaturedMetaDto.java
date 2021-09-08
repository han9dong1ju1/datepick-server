package app.hdj.datepick.domain.featured.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class FeaturedMetaDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;

    @QueryProjection
    public FeaturedMetaDto(Long id, String title, String description, String photoUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
