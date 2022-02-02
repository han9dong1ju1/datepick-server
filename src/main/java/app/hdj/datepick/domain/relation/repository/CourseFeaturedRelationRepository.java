package app.hdj.datepick.domain.relation.repository;

import app.hdj.datepick.domain.featured.entity.Featured;
import app.hdj.datepick.domain.relation.entity.CourseFeaturedRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseFeaturedRelationRepository extends
        JpaRepository<CourseFeaturedRelation, Long>,
        QuerydslPredicateExecutor<CourseFeaturedRelation> {

}
