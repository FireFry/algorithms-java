package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;
import com.burakovv.data.NumberData;

public class RadixSortTest extends AbstractSearchTest {

    @Override
    protected void sort(ComparableData data) {
        RadixSort.sort((NumberData) data);
    }

    @Override
    protected int getRequiredBufferSize(int length) {
        return length;
    }

    @Override
    protected void preprocessArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == Integer.MIN_VALUE) ? Integer.MAX_VALUE : Math.abs(array[i]);
        }
    }
}
