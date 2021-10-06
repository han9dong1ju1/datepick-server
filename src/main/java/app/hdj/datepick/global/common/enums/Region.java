package app.hdj.datepick.global.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum Region {
    지역,
    지역1,
    지역2,
    지역3;

    private static final Map<String, Region> stringToRegion = new HashMap<String, Region>();
    static {
        for (Region region : values()){
            stringToRegion.put(region.name(), region);
        }
    }

    public static Region findByString(String region){
        return stringToRegion.get(region);
    }
}
