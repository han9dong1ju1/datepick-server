package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface FeaturedRepository extends JpaRepository<Featured, Long>, FeaturedSupportRepository,
        QuerydslPredicateExecutor<Featured> {
    List<Featured> findAll();
    Optional<Featured> findById(Long id);

}
