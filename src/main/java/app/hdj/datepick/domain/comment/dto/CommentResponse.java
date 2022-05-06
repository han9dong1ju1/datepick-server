package app.hdj.datepick.domain.comment.dto;

import app.hdj.datepick.domain.comment.entity.Comment;
import app.hdj.datepick.domain.user.dto.UserResponse;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {

    private Long id;
    private String content;
    private Long courseId;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse user;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(),
                                   comment.getContent(),
                                   comment.getCourse().getId(),
                                   comment.getParent() != null ? comment.getParent().getId() : null,
                                   comment.getCreatedAt(),
                                   comment.getUpdatedAt(),
                                   UserResponse.from(comment.getUser()));
    }
}
