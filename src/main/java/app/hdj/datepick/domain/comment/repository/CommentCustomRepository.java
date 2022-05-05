package app.hdj.datepick.domain.comment.repository;

import app.hdj.datepick.domain.comment.dto.CommentFilterParam;
import app.hdj.datepick.domain.comment.entity.Comment;
import app.hdj.datepick.global.common.PagingParam;
import org.springframework.data.domain.Page;

public interface CommentCustomRepository {

    Page<Comment> findCommentPage(PagingParam pagingParam, CommentFilterParam commentFilterParam);

    Page<Comment> findMyCommentPage(PagingParam pagingParam, Long userId);
}
