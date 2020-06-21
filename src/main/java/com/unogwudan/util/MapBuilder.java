package com.unogwudan.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder<K,V> {

    private Map<K,V> map;

    public MapBuilder(Class<? extends Map<K,V>> t) {
        try {
            this.map = t.newInstance();


        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public MapBuilder() {
        this.map = new LinkedHashMap<>();
    }

    public MapBuilder<K,V> a(K key, V value){
        this.map.put(key,value);
        return this;
    }

    public MapBuilder<K,V> a(K key){
        this.map.put(key, (V) key);
        return this;
    }

    public Map<K,V> get(){
        return map;
    }
}
