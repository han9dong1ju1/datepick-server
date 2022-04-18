package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.enums.Provider;

public interface OAuthUserInfo {
    String getUid();

    String getName();

    String getEmail();

    Provider getProvider();

    Gender getGender();
}
