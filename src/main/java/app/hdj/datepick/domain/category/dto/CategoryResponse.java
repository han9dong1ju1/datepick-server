package app.hdj.datepick.domain.category.dto;

import app.hdj.datepick.domain.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
