package app.hdj.datepick.domain.category.service;

import app.hdj.datepick.domain.category.dto.CategoryWithCountResponse;
import app.hdj.datepick.domain.category.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryWithCountResponse> getCategoryList() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryWithCountResponse::from)
            .collect(Collectors.toList());
    }
}
