package app.hdj.datepick.global.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    UNKNOWN('U'),
    MALE('M'),
    FEMALE('F');

    private final Character code;

    @JsonCreator
    Gender(@JsonProperty("gender") Character code) {
        this.code = code;
    }

    @JsonValue
    public Character getCode() {
        return code;
    }
}
