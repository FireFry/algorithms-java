package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class InsertionSortTest extends AbstractSearchTest {
    @Override
    protected void sort(ComparableData comparableData) {
        InsertionSort.sort(comparableData);
    }

    @Override
    protected boolean isFast() {
        return false;
    }
}
