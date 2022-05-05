package app.hdj.datepick.domain.comment.repository;

import static app.hdj.datepick.domain.comment.entity.QComment.comment;

import app.hdj.datepick.domain.comment.dto.CommentFilterParam;
import app.hdj.datepick.domain.comment.entity.Comment;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.util.PagingUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final PagingUtil pagingUtil;

    @Override
    public Page<Comment> findCommentPage(
        PagingParam pagingParam, CommentFilterParam commentFilterParam
    ) {
        JPAQuery<Comment> query = jpaQueryFactory.selectFrom(comment);
        query = filterComments(query, commentFilterParam);
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(),
                                                 Sort.by("createdAt").descending());
        return pagingUtil.getPageImpl(pageRequest, query);
    }

    private JPAQuery<Comment> filterComments(
        JPAQuery<Comment> query, CommentFilterParam commentFilterParam
    ) {
        query = filterCourse(commentFilterParam.getCourseId(), query);
        if (commentFilterParam.getParentId() != null) {
            query = filterParent(commentFilterParam.getParentId(), query);
        } else {
            query = query.where(comment.parent.isNull());
        }
        return query;
    }

    private JPAQuery<Comment> filterCourse(Long courseId, JPAQuery<Comment> query) {
        return query.where(comment.course.id.eq(courseId));
    }

    private JPAQuery<Comment> filterParent(Long parentId, JPAQuery<Comment> query) {
        return query.where(comment.parent.id.eq(parentId));
    }

    @Override
    public Page<Comment> findMyCommentPage(PagingParam pagingParam, Long userId) {
        JPAQuery<Comment> query = jpaQueryFactory.selectFrom(comment)
            .where(comment.user.id.eq(userId));
        PageRequest pageRequest = PageRequest.of(pagingParam.getPage(), pagingParam.getSize(),
                                                 Sort.by("createdAt").descending());
        return pagingUtil.getPageImpl(pageRequest, query);
    }
}
