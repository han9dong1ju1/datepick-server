package app.hdj.datepick.domain.featured.repository;

import app.hdj.datepick.domain.featured.dto.FeaturedMeta;
import app.hdj.datepick.domain.featured.entity.Featured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FeaturedRepository extends
        JpaRepository<Featured, Long>,
        FeaturedCustomRepository,
        QuerydslPredicateExecutor<Featured> {

    List<Featured> findAll();

}
