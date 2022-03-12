package app.hdj.datepick.domain.category.service;

import app.hdj.datepick.domain.category.entity.Category;
import app.hdj.datepick.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

}
