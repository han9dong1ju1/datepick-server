
package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface FeaturedRepository extends
        JpaRepository<Featured, Long>,
        FeaturedCustomRepository,
        QuerydslPredicateExecutor<Featured> {

    Page<Featured> findByIsPinnedOrderByCreatedAtAsc(Boolean isPinned, Pageable pageable);
//    <T> List<T> findAllBy(Class<T> type);
//
//    <T> List<T> findAllByIsPinnedTrue(Class<T> type);
//
//    <T> Optional<T> findById(Long id, Class<T> type);

}
