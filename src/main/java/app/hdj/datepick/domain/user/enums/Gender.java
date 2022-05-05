package app.hdj.datepick.domain.user.enums;

public enum Gender {
    M, F,
    ;

    public static Gender from(String value) {
        for (Gender gender : values()) {
            if (gender.name().equals(value)) {
                return gender;
            }
        }
        return null;
    }
}
