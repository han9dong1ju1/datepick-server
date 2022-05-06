package app.hdj.datepick.domain.category.dto;

import app.hdj.datepick.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryWithCountResponse {

    private Long id;
    private String name;
    private Long placeCount;

    public static CategoryWithCountResponse from(Category category) {
        return new CategoryWithCountResponse(category.getId(),
                                             category.getName(),
                                             (long) category.getCategoryPlaces().size());
    }
}
