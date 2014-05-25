package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;
import org.junit.Assert;

import java.util.Comparator;

import static com.burakovv.algorithms.RedBlackTreeSet.NIL;

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
            assertCorresct(set);
            Assert.assertTrue(isBalanced(set));
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

    public static void assertCorresct(RedBlackTreeSet set) {
        if (set.root.color == RedBlackTreeSet.Color.RED) {
            throw new RuntimeException("The root is red");
        }
        findBlacks(set.root, 0);
    }

    private static int findBlacks(RedBlackTreeSet.Node node, int current) {
        if (node.color == RedBlackTreeSet.Color.BLACK) {
            current++;
        }
        if (node == NIL) {
            return current;
        }
        if (node.color == RedBlackTreeSet.Color.RED && (node.left.color == RedBlackTreeSet.Color.RED || node.right.color == RedBlackTreeSet.Color.RED)) {
            throw new RuntimeException("Red has red child");
        }
        int leftBlacks = findBlacks(node.left, current);
        int rightBlacks = findBlacks(node.right, current);
        if (leftBlacks != rightBlacks) {
            throw new RuntimeException("Tree is not balanced");
        }
        return leftBlacks;
    }

    public static boolean isBalanced(RedBlackTreeSet set) {
        int minWay = minWayLength(set.root, 0);
        int maxWay = maxWayLength(set.root, 0);
        return 2 * minWay >= maxWay;
    }

    private static int minWayLength(RedBlackTreeSet.Node node, int current) {
        return node == NIL ? current : Math.min(
                minWayLength(node.left, current + 1),
                minWayLength(node.right, current + 1));
    }

    private static int maxWayLength(RedBlackTreeSet.Node node, int current) {
        return node == NIL ? current : Math.max(
                maxWayLength(node.left, current + 1),
                maxWayLength(node.right, current + 1));
    }
}
