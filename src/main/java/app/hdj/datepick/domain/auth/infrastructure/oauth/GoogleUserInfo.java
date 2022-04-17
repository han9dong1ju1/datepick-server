package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.enums.Provider;
import lombok.*;

import java.util.Map;

public class GoogleUserInfo implements OAuthUserInfo {

    private final String uid;
    private final String name;
    private final String email;
    private static final Provider provider = Provider.GOOGLE;

    @Builder
    public GoogleUserInfo(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public static GoogleUserInfo from(Map<String, Object> body) {
        String uid = (String) body.get("id");
        String name = ((String) body.get("name")).replaceAll("\\s+", "");
        String email = (String) body.get("email");
        return GoogleUserInfo.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .build();
    }

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
