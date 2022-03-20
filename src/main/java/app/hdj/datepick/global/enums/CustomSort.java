package app.hdj.datepick.global.enums;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

import static app.hdj.datepick.domain.place.entity.QPlace.place;

@Getter
public enum CustomSort {
    LATEST,
    PICK,
    POPULAR,
    RATING_DESC,
    RATING_ASC,
    DISTANCE_ASC,
    ;

    public static CustomSort from(String value) {
        for (CustomSort customSort : values()) {
            if (customSort.name().equalsIgnoreCase(value)) {
                return customSort;
            }
        }
        return null;
    }

    public static OrderSpecifier toPlaceSort(CustomSort customSort) {

        switch (customSort) {
            case PICK:
                return place.pickCount.desc();
            case DISTANCE_ASC:
                return null;
            case POPULAR:
                return null;
            case RATING_ASC:
                return place.rating.asc();
            case RATING_DESC:
                return place.rating.desc();
            case LATEST:
                return place.createdAt.desc();
        }
        return null;
    }

}
