package in.taskoo.core.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
    private final Map<String, String> map = new HashMap<>();

    public MapBuilder add(final String key, final Object value) {
        map.put(key, value != null ? value.toString() : null);
        return this;
    }

    public Map<String, String> build() {
        return map;
    }
}
