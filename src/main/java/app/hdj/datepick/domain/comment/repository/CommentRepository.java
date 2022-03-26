package app.hdj.datepick.domain.comment.repository;

import app.hdj.datepick.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommentRepository extends
        JpaRepository<Comment, Long>,
        CommentCustomRepository,
        QuerydslPredicateExecutor<Comment> {
}
