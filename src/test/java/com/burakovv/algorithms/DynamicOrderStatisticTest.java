package com.burakovv.algorithms;

import com.burakovv.data.DynamicOrderStatistic;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DynamicOrderStatisticTest {

    @Test
    public void testRandom() {
        Comparator<Integer> integerComparator = Comparator.<Integer>naturalOrder();
        DynamicOrderStatistic<Integer> statistic = createDynamicOrderStatistic(integerComparator);
        List<Integer> sample = new ArrayList<Integer>();
        Random random = new Random(21303242);
        for (int i = 0; i < 1000000; i++) {
            Integer element = random.nextInt(1000);
            switch (random.nextInt(5)) {
                case 0:
                    statistic.add(element);
                    sample.add(element);
                    sample.sort(integerComparator);
                    break;
                default:
                    statistic.remove(element);
                    sample.remove(element);
                    break;
            }
            if (!sample.isEmpty()) {
                Integer k = random.nextInt(sample.size());
                Assert.assertEquals(statistic.select(k), sample.get(k));
            }
        }
    }

    private <E> DynamicOrderStatistic<E> createDynamicOrderStatistic(Comparator<E> integerComparator) {
        return new OrderStatisticTree<E>(integerComparator);
    }

}
