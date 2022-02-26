package app.hdj.datepick.global.enums;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum CustomSort {
    LATEST,
    PICK,
    POPULAR,
    RATING_DESC,
    RATING_ASC,
    DISTANCE_ASC,
    DISTANCE_DESC,
    ;

    public static CustomSort from(String value) {
        for (CustomSort customSort : values()) {
            if (customSort.name().equalsIgnoreCase(value)) {
                return customSort;
            }
        }
        return null;
    }
    public static Sort toSort(CustomSort customSort){
        if (customSort == CustomSort.PICK) {
            return Sort.by("pickCount").descending();
        }
        else if (customSort == CustomSort.DISTANCE_ASC) {
            return Sort.by("distance").ascending();
        }
        else if (customSort == CustomSort.POPULAR) {
            return Sort.by("popularity").descending();
        }
        else if (customSort == CustomSort.RATING_ASC) {
            return Sort.by("rating").ascending();
        }
        else if (customSort == CustomSort.RATING_DESC) {
            return Sort.by("rating").descending();
        } else {
            return Sort.by("createdAt").descending();
        }

    }

}
