package com.burakovv.algorithms;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SegmentTreeRMQTest extends TestCase {

    private static final TestFunction GCD_FUNCTION = new TestFunction() {
        @Override
        public int func(int a, int b) {
            return (b == 0) ? a : func(b, a % b);
        }
    };

    private static final TestFunction MIN_FUNCTION = new TestFunction() {
        @Override
        public int func(int a, int b) {
            return Math.min(a, b);
        }
    };

    private static final TestFunction MAX_FUNCTION = new TestFunction() {
        @Override
        public int func(int a, int b) {
            return Math.max(a, b);
        }
    };

    @Test
    public void test() {
        SegmentTreeRMQ<Integer> segmentTree = new SegmentTreeRMQ<Integer>(MAX_FUNCTION.createRMQFunction(), 8);
        segmentTree.set(0, 1);
        segmentTree.set(1, 3);
        segmentTree.set(2, 2);
        segmentTree.set(3, 6);
        segmentTree.set(4, 2);
        segmentTree.set(5, 1);
        segmentTree.set(6, 5);
        segmentTree.set(7, 3);
        assertEquals(1, segmentTree.composeRange(0, 0).intValue());
        assertEquals(3, segmentTree.composeRange(0, 1).intValue());
        assertEquals(2, segmentTree.composeRange(2, 2).intValue());
        assertEquals(6, segmentTree.composeRange(3, 5).intValue());
        assertEquals(3, segmentTree.composeRange(1, 2).intValue());
        assertEquals(5, segmentTree.composeRange(4, 7).intValue());
        assertEquals(5, segmentTree.composeRange(4, 6).intValue());
        assertEquals(2, segmentTree.composeRange(4, 5).intValue());
        assertEquals(3, segmentTree.composeRange(7, 7).intValue());
    }

    @Test
    public void testCorrectness() {
        testLargeData(10000, 10000, true, GCD_FUNCTION);
        testLargeData(10000, 10000, true, MAX_FUNCTION);
        testLargeData(10000, 10000, true, MIN_FUNCTION);
    }

    @Test(timeout = 5000)
    public void testSpeed() {
        testLargeData(100000, 1000000, false, GCD_FUNCTION);
        testLargeData(100000, 1000000, false, MAX_FUNCTION);
        testLargeData(100000, 1000000, false, MIN_FUNCTION);
    }

    public void testLargeData(int size, int iterations, boolean checkCorrectness, TestFunction function) {
        Random random = new Random(315926);
        SegmentTreeRMQ<Integer> table = new SegmentTreeRMQ<Integer>(function.createRMQFunction(), size);
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(Integer.MAX_VALUE);
            table.set(i, value);
            list.add(value);
        }
        for (int i = 0; i < iterations; i++) {

            int index = random.nextInt(size);
            int value = random.nextInt(Integer.MAX_VALUE);
            table.set(index, value);
            list.set(index, value);

            int left = random.nextInt(size);
            int right = left + random.nextInt(size - left);
            Integer select = table.composeRange(left, right);
            if (checkCorrectness) {
                assertEquals(function.composeRange(list, left, right), select);
            } else {
                assertNotNull(select);
            }
        }
    }

    private static abstract class TestFunction {
        public SegmentTreeRMQ.Composer<Integer> createRMQFunction() {
            return new SegmentTreeRMQ.Composer<Integer>() {
                @Override
                public Integer compose(Integer a, Integer b) {
                    return func(a, b);
                }
            };
        }

        public Integer composeRange(List<Integer> list, int left, int right) {
            if (left == right) {
                return list.get(left);
            }
            int middle = (left + right) / 2;
            return funcOrNull(composeRange(list, left, middle), composeRange(list, middle + 1, right));
        }

        private Integer funcOrNull(Integer a, Integer b) {
            return (a == null || b == null) ? null : func(a, b);
        }

        abstract int func(int select, int select1);
    }

}