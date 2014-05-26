package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;
import org.junit.Assert;

import java.util.Comparator;

public class AvlTreeSetTest extends CustomSetTest {
    private static final boolean IS_SPEED_TEST = false;

    @Override
    protected <T> CustomSet<T> createCustomSet(Comparator<T> comparator) {
        return new CustomSetFromMap<T>(new AvlTreeMap<T, Object>(comparator));
    }

    @Override
    protected <T> void afterIteration(CustomSet<T> customSet) {
        super.afterIteration(customSet);
        if (!IS_SPEED_TEST) {
            checkBalanced(((AvlTreeMap) ((CustomSetFromMap) customSet).getMap()).root, 0);
        }
    }

    public static int checkBalanced(AvlTreeMap.Node node, int current) {
        if (node == null) {
            return current;
        }
        Assert.assertTrue(Math.abs((node.left == null ? 0 : node.left.height) - (node.right == null ? 0 : node.right.height)) < 2);
        int max = Math.max(
                checkBalanced(node.left, current + 1),
                checkBalanced(node.right, current + 1));
        Assert.assertEquals(node.height, max - current);
        return max;
    }

    @Override
    protected int getRandomTestIterations() {
        return IS_SPEED_TEST ? 5000000 : 50000;
    }

    @Override
    protected int getIncDecTestIterations() {
        return IS_SPEED_TEST ? 5000000 : 50000;
    }

    @Override
    protected int getRandomRange() {
        return IS_SPEED_TEST ? 100000 : 1000;
    }
}
