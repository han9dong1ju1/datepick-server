package app.hdj.datepick.global.enums;

public enum Sort {
    ASC,
    DESC;

    public static Sort from(String value) {
        for (Sort sort : values()) {
            if (sort.name().equals(value)) {
                return sort;
            }
        }
        return null;
    }
}
