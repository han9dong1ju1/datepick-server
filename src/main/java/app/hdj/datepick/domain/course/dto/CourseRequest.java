package app.hdj.datepick.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @NotEmpty
    private String title;

    private LocalDateTime meetAt;

    @NotNull
    private Boolean isPrivate;

    private List<@Positive Integer> tagIds;

}
