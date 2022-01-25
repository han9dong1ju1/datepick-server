package app.hdj.datepick.global.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Gender {
    UNKNOWN('U'),
    MALE('M'),
    FEMALE('F');

    private final Character code;

    @JsonCreator
    Gender(@JsonProperty("gender") Character code) {
        this.code = code;
    }

    public String getString() {
        return code.toString();
    }

    private static final Map<String, Gender> reverseLookup =
            Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::getString, Function.identity()));

    public static Gender fromString(final String code) {
        return reverseLookup.get(code);
    }

    @JsonValue
    public Character getCode() {
        return code;
    }
}
