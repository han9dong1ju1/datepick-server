package app.hdj.datepick.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllTokenResponse {

    private String accessToken;
    private long expireIn;
    private String refreshToken;

}
