package app.hdj.datepick.domain.relation.repository;

import app.hdj.datepick.domain.relation.entity.CoursePlaceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePlaceRelationRepository extends
        JpaRepository<CoursePlaceRelation, Long>,
        QuerydslPredicateExecutor<CoursePlaceRelation>{

}

