package app.hdj.datepick.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    GOOGLE,
    KAKAO,
    ;

    public static Provider from(String value) {
        for (Provider provider : values()) {
            if (provider.name().equals(value)) {
                return provider;
            }
        }
        return null;
    }
}
