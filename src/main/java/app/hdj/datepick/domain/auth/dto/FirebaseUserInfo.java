package app.hdj.datepick.domain.auth.dto;

import app.hdj.datepick.global.enums.Gender;
import app.hdj.datepick.global.enums.Provider;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FirebaseUserInfo implements OAuthUserInfo {

    private String uid;
    private String name;
    private String email;
    private Provider provider;

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Provider getProvider() {
        return provider;
    }

    @Override
    public Gender getGender() {
        return null;
    }

}
