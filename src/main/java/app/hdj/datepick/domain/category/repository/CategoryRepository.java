package app.hdj.datepick.domain.category.repository;

import app.hdj.datepick.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoryByNameIn(List<String> categoryNameList);
}
