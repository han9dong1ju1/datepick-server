package app.hdj.datepick.domain.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotEmpty
    private String content;

}
