package app.hdj.datepick.domain.comment.dto;

import app.hdj.datepick.domain.comment.entity.Comment;
import app.hdj.datepick.domain.user.dto.UserPublic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentPublic {

    private Long id;
    private String content;
    private Long courseId;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserPublic user;

    public static CommentPublic from(Comment comment) {
        UserPublic user = new UserPublic(
                comment.getUser().getId(),
                comment.getUser().getNickname(),
                comment.getUser().getGender(),
                comment.getUser().getImageUrl()
        );
        return new CommentPublic(
                comment.getId(),
                comment.getContent(),
                comment.getCourse().getId(),
                comment.getParent() != null ? comment.getParent().getId() : null,
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                user
        );
    }

}
