package app.hdj.datepick.domain.comment.controller;

import app.hdj.datepick.domain.auth.annotation.AuthPrincipal;
import app.hdj.datepick.domain.auth.annotation.Authorize;
import app.hdj.datepick.domain.comment.dto.CommentFilterParam;
import app.hdj.datepick.domain.comment.dto.CommentPublic;
import app.hdj.datepick.domain.comment.dto.CommentRequest;
import app.hdj.datepick.domain.comment.service.CommentService;
import app.hdj.datepick.domain.user.enums.Role;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    CustomPage<CommentPublic> getCommentPage(
        @Valid PagingParam pagingParam, @Valid CommentFilterParam commentFilterParam
    ) {
        return commentService.getCommentPage(pagingParam, commentFilterParam);
    }

    @Authorize({Role.USER})
    @GetMapping("me")
    CustomPage<CommentPublic> getMyCommentPage(
        @AuthPrincipal Long userId, @Valid PagingParam pagingParam
    ) {
        return commentService.getMyCommentPage(pagingParam, userId);
    }

    @Authorize({Role.USER})
    @PostMapping("")
    CommentPublic addComment(
        @AuthPrincipal Long userId,
        @Valid CommentFilterParam commentFilterParam,
        @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.addComment(commentFilterParam, commentRequest, userId);
    }

    @Authorize({Role.USER})
    @PatchMapping("{commentId}")
    CommentPublic modifyComment(
        @AuthPrincipal Long userId,
        @PathVariable Long commentId,
        @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.modifyComment(commentId, commentRequest, userId);
    }

    @Authorize({Role.USER})
    @DeleteMapping("{commentId}")
    void removeComment(
        @AuthPrincipal Long userId, @PathVariable Long commentId
    ) {
        commentService.removeComment(commentId, userId);
    }
}
