package app.hdj.datepick.domain.category.dto;

import app.hdj.datepick.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;
    private Long placeCount;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(),
                                    (long) category.getCategoryPlaces().size());
    }
}
