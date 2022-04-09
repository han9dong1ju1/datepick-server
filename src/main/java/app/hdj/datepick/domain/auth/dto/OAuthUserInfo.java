package app.hdj.datepick.domain.auth.dto;

import app.hdj.datepick.global.enums.Gender;
import app.hdj.datepick.global.enums.Provider;

public interface OAuthUserInfo {
    String getUid();
    String getName();
    String getEmail();
    Provider getProvider();
    Gender getGender();
}
