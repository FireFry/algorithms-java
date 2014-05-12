package com.burakovv.algorithms;

public class BinarySearch {

    public static int search(int element, int[] array) {
        return search(element, array, 0, array.length);
    }

    public static int search(int element, int[] array, int offset, int size) {
        int left = offset;
        int right = offset + size - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int compareResult = element - array[middle];
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
