package app.hdj.datepick.domain.category.dto;

import app.hdj.datepick.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategorySimpleResponse {

    private Long id;
    private String name;

    public static CategorySimpleResponse from(Category category) {
        return new CategorySimpleResponse(
                category.getId(),
                category.getName()
        );
    }

}
