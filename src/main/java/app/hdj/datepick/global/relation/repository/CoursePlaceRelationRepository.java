package app.hdj.datepick.global.relation.repository;

import app.hdj.datepick.global.relation.entity.CoursePlaceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePlaceRelationRepository extends
        JpaRepository<CoursePlaceRelation, Long>,
        QuerydslPredicateExecutor<CoursePlaceRelation>{

}

