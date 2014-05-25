package com.burakovv.algorithms;

import org.junit.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeSetTest {

    @Test(timeout = 30000)
    public void testRandom() {
        doTest(new Provider() {
            private final Random random = new Random(31415926);

            @Override
            public int nextValue() {
                return random.nextInt(100000);
            }

            @Override
            public int nextAction() {
                return random.nextInt(3);
            }

            @Override
            public int getIterations() {
                return 10000000;
            }
        });
    }

    @Test(timeout = 30000)
    public void testInc() {
        doTest(new Provider() {
            private int next = 0;
            private final Random random = new Random(31415926);

            @Override
            public int nextValue() {
                int result = next;
                next = (next + 1) % 100000;
                return result;
            }

            @Override
            public int nextAction() {
                return random.nextInt(3);
            }

            @Override
            public int getIterations() {
                return 40000;
            }
        });
    }

    @Test(timeout = 30000)
    public void testDec() {
        doTest(new Provider() {
            private int next = 99999;
            private final Random random = new Random(31415926);

            @Override
            public int nextValue() {
                int result = next;
                next = next == 0 ? 99999 : next - 1;
                return result;
            }

            @Override
            public int nextAction() {
                return random.nextInt(3);
            }

            @Override
            public int getIterations() {
                return 40000;
            }
        });
    }

    private void doTest(Provider provider) {
        BinarySearchTreeSet<Integer> customSet = new BinarySearchTreeSet<Integer>(Comparator.<Integer>naturalOrder());
        TreeSet<Integer> systemSet = new TreeSet<Integer>();

        long totalSystemTime = 0;
        long totalCustomTime = 0;

        for (int i = 0; i < provider.getIterations(); i++) {
            int action = provider.nextAction();
            int value = provider.nextValue();
            boolean expected = false;
            boolean actual = false;
            long startAt;
            long entAt;
            switch (action) {
                case 0:
                    startAt = System.currentTimeMillis();
                    expected = systemSet.add(value);
                    entAt = System.currentTimeMillis();
                    totalSystemTime += entAt - startAt;

                    startAt = System.currentTimeMillis();
                    actual = customSet.add(value);
                    entAt = System.currentTimeMillis();
                    totalCustomTime += entAt - startAt;

                    break;
                case 1:
                    startAt = System.currentTimeMillis();
                    expected = systemSet.contains(value);
                    entAt = System.currentTimeMillis();
                    totalSystemTime += entAt - startAt;

                    startAt = System.currentTimeMillis();
                    actual = customSet.contains(value);
                    entAt = System.currentTimeMillis();
                    totalCustomTime += entAt - startAt;

                    break;
                case 2:
                    startAt = System.currentTimeMillis();
                    expected = systemSet.remove(value);
                    entAt = System.currentTimeMillis();
                    totalSystemTime += entAt - startAt;

                    startAt = System.currentTimeMillis();
                    actual = customSet.remove(value);
                    entAt = System.currentTimeMillis();
                    totalCustomTime += entAt - startAt;
                    break;
            }
            assertEquals(expected, actual);
        }

        System.out.println("Total system time: " + totalSystemTime);
        System.out.println("Total custom time: " + totalCustomTime);
    }

    private static interface Provider {
        int nextValue();
        int nextAction();
        int getIterations();
    }

}
