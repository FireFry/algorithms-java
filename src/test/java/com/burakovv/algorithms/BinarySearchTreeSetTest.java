package com.burakovv.algorithms;

import org.junit.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeSetTest {

    @Test
    public void testRandom() {
        BinarySearchTreeSet<Integer> customSet = new BinarySearchTreeSet<Integer>(Comparator.<Integer>naturalOrder());
        TreeSet<Integer> systemSet = new TreeSet<Integer>();
        Random random = new Random(31415926);

        long totalSystemTime = 0;
        long totalCustomTime = 0;

        for (int i = 0; i < 1000000; i++) {
            int action = random.nextInt(3);
            int value = random.nextInt(30);
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

}
