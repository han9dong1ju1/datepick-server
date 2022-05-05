package app.hdj.datepick.domain.auth.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnregisterRequest {

    @NotNull
    private String reason;
    private String content;
}
