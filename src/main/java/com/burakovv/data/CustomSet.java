package com.burakovv.data;

public interface CustomSet<E> {
    boolean add(E value);

    boolean remove(Object value);

    boolean contains(E value);
}
