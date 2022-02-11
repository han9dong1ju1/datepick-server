package app.hdj.datepick.domain.place.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryPage {

    private Long id;
    private String name;

    @QueryProjection
    public CategoryPage(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
