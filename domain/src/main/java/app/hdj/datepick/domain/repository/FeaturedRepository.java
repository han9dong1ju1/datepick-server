package app.hdj.datepick.domain.repository;

import app.hdj.datepick.domain.dto.FeaturedDetail;
import app.hdj.datepick.domain.entity.table.Featured;
import app.hdj.datepick.domain.repository.support.FeaturedSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface FeaturedRepository extends JpaRepository<Featured, Long>, FeaturedSupportRepository,
        QuerydslPredicateExecutor<Featured> {
    List<Featured> findAll();
    Optional<Featured> findById(Long id);

}
