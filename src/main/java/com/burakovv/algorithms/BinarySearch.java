package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class BinarySearch {

    /**
     * Searching key should be the first buffer element
     */
    public static int search(ComparableData data) {
        int left = data.getOffset();
        int right = left + data.getSize() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int compareResult = data.compare(data.getBufferOffset(), middle);
            if (compareResult == 0) {
                return middle;
            } else if (compareResult < 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -left - 1;
    }

}
