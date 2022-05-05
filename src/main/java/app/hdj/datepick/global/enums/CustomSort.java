package app.hdj.datepick.global.enums;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum CustomSort {
    LATEST, PICK, POPULAR, RATING_DESC, RATING_ASC, DISTANCE,
    ;

    public static CustomSort from(String value) {
        for (CustomSort customSort : values()) {
            if (customSort.name().equalsIgnoreCase(value)) {
                return customSort;
            }
        }
        return null;
    }

    public static Sort toSort(CustomSort customSort) {
        switch (customSort) {
            case PICK:
                return Sort.by("pickCount").descending();
            case DISTANCE:
                return Sort.by("distance").ascending();
            case POPULAR:
                return Sort.by("popularity").descending();
            case RATING_ASC:
                return Sort.by("rating").ascending();
            case RATING_DESC:
                return Sort.by("rating").descending();
            case LATEST:
                return Sort.by("createdAt").descending();
        }
        return Sort.unsorted();
    }

    public static Sort toSort(CustomSort customSort, CustomSort defaultSort) {
        if (customSort == null) {
            customSort = defaultSort;
        }
        return toSort(customSort);
    }
}
