package com.burakovv.data;

public interface CustomMap<K, V> {

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

}
