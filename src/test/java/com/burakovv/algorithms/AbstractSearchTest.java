package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;
import com.burakovv.data.impl.IntArrayDataWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public abstract class AbstractSearchTest {

    protected static volatile long maxTime = 0;

    @After
    public void tearDown() {
        System.out.println("Max sorting time: " + maxTime);

    }

    @Test
    public void testEmptyArray() {
        assertSortSucceeded(new int[0]);
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
        int[] array = new int[isFast() ? 1000000 : 10000];
        for (int i = 0; i < 5; i++) {
            fillWithRandomData(array, i);
            assertSortSucceeded(array);
        }
    }

    protected boolean isFast() {
        return true;
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
        preprocessArray(array);
        int requiredBufferSize = getRequiredBufferSize(array.length);
        int[] copy = Arrays.copyOf(array, array.length + requiredBufferSize);
        IntArrayDataWrapper data = new IntArrayDataWrapper(copy, 0, array.length, array.length, requiredBufferSize);
        long startedAt = System.currentTimeMillis();
        sort(data);
        long endAt = System.currentTimeMillis();
        updateMaxTime(endAt - startedAt);
        assertSorted(copy, array.length);
        assertEquals(array, copy);
    }

    protected void preprocessArray(int[] array) {}

    private void updateMaxTime(long delay) {
        maxTime = Math.max(maxTime, delay);
    }

    protected int getRequiredBufferSize(int length) {
        return 0;
    }

    private void assertEquals(int[] a, int[] b) {
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            Assert.assertEquals(a[i], b[i]);
        }
    }

    private void assertSorted(int[] array, int length) {
        for (int i = 1; i < length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    protected abstract void sort(ComparableData comparableData);

}
