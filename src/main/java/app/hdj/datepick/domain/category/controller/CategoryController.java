package app.hdj.datepick.domain.category.controller;

import app.hdj.datepick.domain.category.dto.CategoryWithCountResponse;
import app.hdj.datepick.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<CategoryWithCountResponse> getCategoryList() {
        return categoryService.getCategoryList();
    }
}
