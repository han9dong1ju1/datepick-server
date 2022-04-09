package app.hdj.datepick.domain.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class AuthTokenResponse {

    private String token;

    public static AuthTokenResponse from(String token) {
        return new AuthTokenResponse(token);
    }

}
