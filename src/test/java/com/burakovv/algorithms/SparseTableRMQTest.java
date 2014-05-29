package com.burakovv.algorithms;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SparseTableRMQTest extends TestCase {

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
        SparseTableRMQ<Integer> table = SparseTableRMQ.newBuilder(MAX_FUNCTION.createRMQFunction()).add(1, 3, 2, 6, 2, 1, 5, 3).build();
        assertEquals(1, table.select(0, 0).intValue());
        assertEquals(3, table.select(0, 1).intValue());
        assertEquals(2, table.select(2, 2).intValue());
        assertEquals(6, table.select(3, 5).intValue());
        assertEquals(3, table.select(1, 2).intValue());
        assertEquals(5, table.select(4, 7).intValue());
        assertEquals(5, table.select(4, 6).intValue());
        assertEquals(2, table.select(4, 5).intValue());
        assertEquals(3, table.select(7, 7).intValue());
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
        SparseTableRMQ.Builder<Integer> builder = SparseTableRMQ.newBuilder(function.createRMQFunction());
        Random random = new Random(315926);
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(Integer.MAX_VALUE);
            builder.add(value);
            list.add(value);
        }
        SparseTableRMQ<Integer> table = builder.build();
        for (int i = 0; i < iterations; i++) {
            int left = random.nextInt(size);
            int right = left + random.nextInt(size - left);
            Integer select = table.select(left, right);
            if (checkCorrectness) {
                assertEquals(function.select(list, left, right), select);
            } else {
                assertNotNull(select);
            }
        }
    }

    private static abstract class TestFunction {
        public SparseTableRMQ.Function<Integer> createRMQFunction() {
            return new SparseTableRMQ.Function<Integer>() {
                @Override
                public Integer combine(Integer a, Integer b) {
                    return func(a, b);
                }
            };
        }

        public Integer select(List<Integer> list, int left, int right) {
            if (left == right) {
                return list.get(left);
            }
            int middle = (left + right) / 2;
            return func(select(list, left, middle), select(list, middle + 1, right));
        }

        abstract int func(int select, int select1);
    }

}