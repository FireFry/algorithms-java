package com.burakovv.algorithms;

public class InsertionSortTest extends AbstractSearchTest {
    @Override
    void sort(int[] a, int offset, int size) {
        InsertionSort.sort(a, offset, size);
    }
}
