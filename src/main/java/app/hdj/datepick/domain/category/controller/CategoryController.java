package app.hdj.datepick.domain.category.controller;

import app.hdj.datepick.domain.category.entity.Category;
import app.hdj.datepick.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }

}