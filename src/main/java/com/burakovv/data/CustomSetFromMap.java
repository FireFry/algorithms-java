package com.burakovv.data;

import com.burakovv.data.CustomMap;
import com.burakovv.data.CustomSet;

public class CustomSetFromMap<E> implements CustomSet<E> {
    private static final Object PRESENT = new Object();
    protected final CustomMap<E, Object> map;

    public CustomSetFromMap(CustomMap<E, Object> map) {
        this.map = map;
    }

    public boolean add(E value) {
        return map.put(value, PRESENT) != PRESENT;
    }

    public boolean remove(Object value) {
        return map.remove(value) == PRESENT;
    }

    public boolean contains(E value) {
        return map.get(value) == PRESENT;
    }

    public CustomMap<E, Object> getMap() {
        return map;
    }
}
