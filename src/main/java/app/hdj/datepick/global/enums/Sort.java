package app.hdj.datepick.global.enums;

import lombok.Getter;

@Getter
public enum Sort {
    LATEST,
    PICK,
    POPULAR,
    RATING_DESC,
    RATING_ASC,
    DISTANCE_ASC,
    DISTANCE_DESC,
    ;

    public static Sort from(String value) {
        for (Sort sort : values()) {
            if (sort.name().equalsIgnoreCase(value)) {
                return sort;
            }
        }
        return null;
    }
}
