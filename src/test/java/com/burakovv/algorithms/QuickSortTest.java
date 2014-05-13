package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class QuickSortTest extends AbstractSearchTest {
    @Override
    protected void sort(ComparableData comparableData) {
        QuickSort.sort(comparableData);
    }
}
