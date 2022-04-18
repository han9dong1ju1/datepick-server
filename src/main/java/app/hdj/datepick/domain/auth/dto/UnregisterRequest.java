package app.hdj.datepick.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UnregisterRequest {

    @NotNull
    private String reason;

    private String content;

}
