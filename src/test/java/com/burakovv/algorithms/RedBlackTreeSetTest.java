package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;
import org.junit.Assert;

import java.util.Comparator;

public class RedBlackTreeSetTest extends CustomSetTest {
    private static final boolean IS_SPEED_TEST = false;

    @Override
    protected <T> CustomSet<T> createCustomSet(Comparator<T> comparator) {
        return new RedBlackTreeSet<T>(comparator);
    }

    @Override
    protected <T> void afterIteration(CustomSet<T> customSet) {
        super.afterIteration(customSet);
        if (!IS_SPEED_TEST) {
            RedBlackTreeSet<T> set = (RedBlackTreeSet<T>) customSet;
            set.assertCorresct();
            Assert.assertTrue(set.isBalanced());
        }
    }

    @Override
    protected int getRandomTestIterations() {
        return IS_SPEED_TEST ? 5000000 : 50000;
    }

    @Override
    protected int getIncDecTestIterations() {
        return IS_SPEED_TEST ? 5000000 : 50000;
    }
}
