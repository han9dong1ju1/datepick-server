package app.hdj.datepick.domain.category.repository;

import app.hdj.datepick.domain.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByNameIn(List<String> categoryNameList);
}
