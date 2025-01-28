package com.mystore.datamanager;

import java.util.HashMap;
import java.util.Map;

public class ScenarioData {
    private static final ThreadLocal<Map<String, String>> threadLocalDataMap = ThreadLocal.withInitial(HashMap::new);

    private ScenarioData() {
    }

    public static void setData(String key, String value) {
        threadLocalDataMap.get().put(key, value);
    }

    public static String getData(String key) {
        return threadLocalDataMap.get().get(key);
    }

    public static void clear() {
        threadLocalDataMap.get().clear();
    }
}
