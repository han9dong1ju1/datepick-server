package app.hdj.datepick.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class CommentFilterParam {

    @NotNull
    @Positive
    private Long courseId;

    @Positive
    private Long parentId;

}
