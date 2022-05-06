package app.hdj.datepick.global.enums;

import lombok.Getter;

@Getter
public enum CustomSort {
    LATEST,
    PICK,
    POPULAR,
    RATING_DESC,
    RATING_ASC,
    DISTANCE,
    ;

    public static CustomSort from(String value) {
        for (CustomSort customSort : values()) {
            if (customSort.name().equalsIgnoreCase(value)) {
                return customSort;
            }
        }
        return null;
    }
}
