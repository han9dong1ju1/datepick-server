package app.hdj.datepick.domain.comment.service;

import app.hdj.datepick.domain.comment.dto.CommentFilterParam;
import app.hdj.datepick.domain.comment.dto.CommentPublic;
import app.hdj.datepick.domain.comment.dto.CommentRequest;
import app.hdj.datepick.domain.comment.entity.Comment;
import app.hdj.datepick.domain.comment.repository.CommentRepository;
import app.hdj.datepick.domain.course.entity.Course;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CustomPage<CommentPublic> getCommentPage(PagingParam pagingParam,
                                                    CommentFilterParam commentFilterParam) {
        Page<Comment> commentPage = commentRepository.findCommentPage(pagingParam, commentFilterParam);

        return new CustomPage<>(
                commentPage.getTotalElements(),
                commentPage.getTotalPages(),
                commentPage.getNumber(),
                commentPage.getContent().stream()
                        .map(CommentPublic::from)
                        .collect(Collectors.toList())
        );
    }

    public CustomPage<CommentPublic> getMyCommentPage(PagingParam pagingParam, Long userId) {
        Page<Comment> commentPage = commentRepository.findMyCommentPage(pagingParam, userId);

        return new CustomPage<>(
                commentPage.getTotalElements(),
                commentPage.getTotalPages(),
                commentPage.getNumber(),
                commentPage.getContent().stream()
                        .map(CommentPublic::from)
                        .collect(Collectors.toList())
        );
    }

    public CommentPublic addComment(CommentFilterParam commentFilterParam, CommentRequest commentRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Course course = Course.builder().build();
        course.setId(commentFilterParam.getCourseId());

        Comment parent = null;
        if (commentFilterParam.getParentId() != null) {
            parent = Comment.builder().build();
            parent.setId(commentFilterParam.getParentId());
        }

        Comment comment = Comment.builder()
                .user(user)
                .course(course)
                .content(commentRequest.getContent())
                .parent(parent)
                .build();
        commentRepository.save(comment);

        return CommentPublic.from(comment);
    }

    public CommentPublic modifyComment(Long commentId, CommentRequest commentRequest, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        comment.setContent(commentRequest.getContent());

        return CommentPublic.from(comment);
    }

    public void removeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        commentRepository.delete(comment);
    }

}
