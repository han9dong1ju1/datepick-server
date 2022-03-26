package app.hdj.datepick.domain.comment.controller;

import app.hdj.datepick.domain.comment.dto.CommentFilterParam;
import app.hdj.datepick.domain.comment.dto.CommentPublic;
import app.hdj.datepick.domain.comment.dto.CommentRequest;
import app.hdj.datepick.domain.comment.service.CommentService;
import app.hdj.datepick.global.common.CustomPage;
import app.hdj.datepick.global.common.PagingParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    CustomPage<CommentPublic> getCommentPage(@Valid PagingParam pagingParam,
                                             @Valid CommentFilterParam commentFilterParam) {
        return commentService.getCommentPage(pagingParam, commentFilterParam);
    }

    @GetMapping("me")
    CustomPage<CommentPublic> getMyCommentPage(@AuthenticationPrincipal Long userId,
                                               @Valid PagingParam pagingParam) {
        return commentService.getMyCommentPage(pagingParam, userId);
    }

    @PostMapping("")
    CommentPublic addComment(@AuthenticationPrincipal Long userId,
                             @Valid CommentFilterParam commentFilterParam,
                             @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentFilterParam, commentRequest, userId);
    }

    @PatchMapping("{commentId}")
    CommentPublic modifyComment(@AuthenticationPrincipal Long userId,
                                @PathVariable Long commentId,
                                @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.modifyComment(commentId, commentRequest, userId);
    }

    @DeleteMapping("{commentId}")
    void removeComment(@AuthenticationPrincipal Long userId,
                       @PathVariable Long commentId) {
        commentService.removeComment(commentId, userId);
    }

}
