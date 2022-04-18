package app.hdj.datepick.domain.place.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;

    @QueryProjection
    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
