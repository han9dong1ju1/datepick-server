package app.hdj.datepick.domain.comment.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentFilterParam {

    @NotNull
    @Positive
    private Long courseId;
    @Positive
    private Long parentId;
}
