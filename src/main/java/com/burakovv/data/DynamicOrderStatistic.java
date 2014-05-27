package com.burakovv.data;

public interface DynamicOrderStatistic<E> {

    void add(E element);

    boolean remove(E element);

    E select(int k);

}
