package app.hdj.datepick.global.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum Style {
    스타일1,
    스타일2,
    스타일3;

    private static final Map<String, Style> stringToStyle = new HashMap<String, Style>();
    static {
        for (Style style : values()){
            stringToStyle.put(style.name(), style);
        }
    }

    public static Style findByString(String style){
        return stringToStyle.get(style);
    }
}
