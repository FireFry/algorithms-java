package com.burakovv.algorithms;

import com.burakovv.data.NumberData;

import java.util.Arrays;

public class RadixSort {

    /**
     * <pre>{@code data.getBufferSize() >= data.getSize()}</pre>
     * <pos>{@code data} is sorted</pos>
     */
    public static void sort(NumberData data) {
        int[] count = new int[10];
        long m = 1;
        boolean nonzeroValuesFound;
        do {
            nonzeroValuesFound = false;
            Arrays.fill(count, 0);
            for (int i = 0, limit = data.getSize(); i < limit; i++) {
                long number = data.getNumber(data.getOffset() + i) / m;
                if (number > 0) {
                    nonzeroValuesFound = true;
                }
                count[((int) (number % 10))]++;
            }
            if (nonzeroValuesFound) {
                for (int i = 0, limit = data.getSize(); i < limit; i++) {
                    data.swap(data.getOffset() + i, data.getBufferOffset() + i);
                }
                for (int i = 1; i < count.length; i++) {
                    count[i] += count[i - 1];
                }
                for (int i = data.getSize() - 1; i >= 0; i--) {
                    data.swap(data.getBufferOffset() + i, data.getOffset() + (count[((int) ((data.getNumber(data.getBufferOffset() + i) / m) % 10))]--) - 1);
                }
                m *= 10;
            }
        } while (nonzeroValuesFound);
    }

}
