package app.hdj.datepick.domain.comment.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotEmpty
    private String content;
}
