package bdd.support;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private final Map<String, Object> memory = new HashMap<>();

    public void put(String key, Object value) {
        memory.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) memory.get(key);
    }

    public boolean contains(String key) {
        return memory.containsKey(key);
    }
}