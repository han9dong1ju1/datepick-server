package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.enums.Provider;
import lombok.Builder;

public class KakaoUserInfo implements OAuthUserInfo {

    private final String uid;
    private final String name;
    private final String email;
    private final static Provider provider = Provider.KAKAO;

    @Builder
    private KakaoUserInfo(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
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
