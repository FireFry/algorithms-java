package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;
import com.burakovv.data.impl.IntArrayDataWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class OrderStatisticTest extends Assert {

    @Test
    public void testSmallSequences() {
        Random random = new Random(31415927);
        int[] a = new int[8];
        int[] b = new int[a.length];
        for (int size = 1; size <= a.length; size++) {
            for (int i = 0; i < size; i++) {
                a[i] = random.nextInt();
                b[i] = a[i];
            }
            QuickSort.sort(new IntArrayDataWrapper(b, 0, size, size, 0));
            ComparableData data = new IntArrayDataWrapper(a, 0, size, size, 0);
            for (int test = 0; test < 1000; test++) {
                for (int i = 0; i < size - 1; i++) {
                    for (int j = i + 1; j < size; j++) {
                        if (random.nextBoolean()) {
                            data.swap(i, j);
                        }
                    }
                }
                int k = random.nextInt(size);
                int i = OrderStatistic.select(data, k);
                assertEquals(a[i], b[k]);
            }
        }
    }

}
