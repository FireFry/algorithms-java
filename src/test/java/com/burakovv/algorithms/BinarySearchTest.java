package com.burakovv.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.burakovv.algorithms.BinarySearch.*;

public class BinarySearchTest {

    @Test
    public void testSearch() throws Exception {
        final int[] array = {1, 3, 5, 7};
        assertEquals(-1, search(0, array));
        assertEquals(0, search(1, array));
        assertEquals(-2, search(2, array));
        assertEquals(1, search(3, array));
        assertEquals(-3, search(4, array));
        assertEquals(2, search(5, array));
        assertEquals(-4, search(6, array));
        assertEquals(3, search(7, array));
        assertEquals(-5, search(8, array));
    }

    @Test
    public void testCustomOffset() {
        final int[] array = {0, 3, 4, 6, 1, 2, 4, 8};
        assertEquals(1, search(3, array, 0, 4));
        assertEquals(1, search(3, array, 0, 3));
        assertEquals(1, search(3, array, 1, 2));
        assertEquals(-2, search(2, array, 0, 4));
        assertEquals(5, search(2, array, 4, 4));
        assertEquals(-4, search(5, array, 0, 4));
        assertEquals(-8, search(5, array, 4, 4));
        assertEquals(-1, search(0, array, 0, 0));
        assertEquals(-6, search(0, array, 5, 0));
    }

    @Test
    public void testBoundaryValues() {
        {
            final int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE};
            assertEquals(0, search(Integer.MIN_VALUE, array));
            assertEquals(-2, search(0, array));
            assertEquals(1, search(Integer.MAX_VALUE, array));
        }

        {
            final int[] array = {-2147483648, -1073741823, 1073741823, 2147483647};
            assertEquals(0, search(-2147483648, array));
            assertEquals(1, search(-1073741823, array));
            assertEquals(2, search(1073741823, array));
            assertEquals(3, search(2147483647, array));
        }
    }

}
