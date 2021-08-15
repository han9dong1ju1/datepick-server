package app.hdj.datepick.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterResponseDto {

    @NotEmpty private String customToken;

    // TODO: 추가 속성 정의

}
