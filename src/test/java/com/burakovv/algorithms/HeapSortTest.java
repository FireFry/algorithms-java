package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class HeapSortTest extends AbstractSearchTest {
    @Override
    protected void sort(ComparableData comparableData) {
        HeapSort.sort(comparableData);
    }
}
