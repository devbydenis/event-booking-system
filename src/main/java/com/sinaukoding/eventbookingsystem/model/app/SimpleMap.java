package com.sinaukoding.eventbookingsystem.model.app;

import java.util.HashMap;
import java.util.Map;

public class SimpleMap extends HashMap<String, Object> {

    // Factory method -> membuat instance
    public static SimpleMap createMap() {
        return new SimpleMap();
    }
    public static SimpleMap createMap(String key, Object val) {
        return createMap().add(key, val);
    }

    // Method chaining
    /*
    * // Cara lama - bertele-tele
    *    SimpleMap data = new SimpleMap();
    *    data.put("id", "123");
    *    data.put("nama", "John");
    *    data.put("email", "john@email.com");
    *
    *    // Cara baru - elegant & singkat
    *    SimpleMap data = SimpleMap.createMap()
    *        .add("id", "123")
    *        .add("nama", "John")
    *        .add("email", "john@email.com");
    * */
    public SimpleMap add(String key, Object val) {
        this.put(key, val);
        return this;    // ← Kunci method chaining
    }

    /*
    * SimpleMap data = SimpleMap.createMap("nama", "John");
    *
    *  // Tidak akan override karena "nama" sudah ada
    * data.addIfNoExist("nama", "Jane"); // "nama" tetap "John"
    *
    * // Akan ditambahkan karena "email" belum ada
    * data.addIfNoExist("email", "john@email.com"); // Email ditambahkan
    * */
    public SimpleMap addIfNoExist(String key, Object val) {
        if (!this.containsKey(key)) {
            this.put(key, val);
        }
        return this;
    }

    // Type-Safe Get
    /*
        // HashMap biasa - risky!
        Object value = map.get("age");
        Integer age = (Integer) value; // Bisa ClassCastException!
        // SimpleMap - type safe!
        Integer age = map.get("age", Integer.class);
        String nama = map.getOrDefault("nama", "Unknown", String.class);
    */
    public <T> T get(String key, Class<T> tClass) {
        if (!this.containsKey(key))
            return null;
        return tClass.cast(this.get(key));
    }
    public <T> T getOrDefault(String key, T defaultValue, Class<T> tClass) {
        if (!this.containsKey(key))
            return defaultValue;
        return tClass.cast(this.get(key));
    }

    // Convert ke simple map
    public static SimpleMap from(Map<String, Object> origin) {
        SimpleMap map = new SimpleMap();
        map.putAll(origin);
        return map;
    }

}
