package app.hdj.datepick.domain.category.service;

import app.hdj.datepick.domain.category.dto.CategoryResponse;
import app.hdj.datepick.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategoryList() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).collect(Collectors.toList());
    }

}
