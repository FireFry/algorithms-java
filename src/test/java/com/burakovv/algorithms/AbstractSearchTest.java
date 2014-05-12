package com.burakovv.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public abstract class AbstractSearchTest {

    @Test
    public void testEmptyArray() {
        final int[] array = new int[0];
        sort(array);
    }

    @Test
    public void testSmallArray() {
        assertSortSucceeded(new int[] {1, 1, 2, 3, 5, 7, 12, 19});
        assertSortSucceeded(new int[] {2, 19, 3, 6, 1, 4, 7, 12});
    }

    @Test
    public void testBoundaryValues() {
        assertSortSucceeded(new int[] { 0, Integer.MIN_VALUE, Integer.MAX_VALUE });
        assertSortSucceeded(new int[] { 0, Integer.MAX_VALUE, Integer.MIN_VALUE });
        assertSortSucceeded(new int[] { Integer.MIN_VALUE, 0, Integer.MAX_VALUE });
        assertSortSucceeded(new int[] { Integer.MAX_VALUE, 0, Integer.MIN_VALUE });
        assertSortSucceeded(new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 });
        assertSortSucceeded(new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 });
    }

    @Test
    public void testLargeArray() {
        int[] array = new int[5000000];
        for (int i = 0; i < 5; i++) {
            fillWithRandomData(array, i);
        }
    }

    private void fillWithRandomData(int[] array, int source) {
        Random random = new Random(source * 17);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        array[random.nextInt(array.length)] = Integer.MIN_VALUE;
        array[random.nextInt(array.length)] = Integer.MAX_VALUE;
        array[random.nextInt(array.length)] = 0;
    }

    private void assertSortSucceeded(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        sort(array);
        assertSorted(array);
        assertEqualsIgnoringOrder(copy, array);
    }

    private void assertEqualsIgnoringOrder(int[] a, int[] b) {
        boolean[] flag = new boolean[a.length];
        for (int i = 0; i < a.length; i++) {
            int j = 0;
            while (j < b.length && flag[j] && b[j] != a[j]) {
                j++;
            }
            assertTrue(j < b.length);
            flag[j] = true;
        }
    }

    private void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    abstract void sort(int[] a, int offset, int size);

    protected void sort(int[] a) {
        sort(a, 0, a.length);
    }

}
