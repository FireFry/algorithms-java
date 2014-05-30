package com.burakovv.algorithms;

public class SegmentTreeRMQ<E> {
    private final Composer<E> composer;
    private final E[] a;
    private final int offset;

    public SegmentTreeRMQ(Composer<E> composer, int capacity) {
        this.composer = composer;
        capacity = validCapacity(capacity);
        a = (E[]) new Object[capacity * 2 - 1];
        offset = capacity - 1;
    }

    public void set(int index, E value) {
        int i = offset + index;
        a[i] = value;
        while (i > 0) {
            int p = parent(i);
            E newValue = compose(a[left(p)], a[right(p)]);
            if (a[p] == newValue) {
                break;
            }
            a[p] = newValue;
            i = p;
        }
    }

    protected int parent(int i) {
        return (i - 1) / 2;
    }

    protected int left(int p) {
        return 2 * p + 1;
    }

    protected int right(int p) {
        return 2 * p + 2;
    }

    public E composeRange(int left, int right) {
        return composeRange(left, right, 0, 0, offset);
    }

    private E composeRange(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (left <= nodeLeft && right >= nodeRight) {
            return a[node];
        }
        int middle = (nodeLeft + nodeRight) / 2;

        E leftRange = null;
        boolean hasLeftRange = false;

        if (middle >= left) {
            hasLeftRange = true;
            leftRange = composeRange(left, right, left(node), nodeLeft, middle);
        }

        E rightRange;

        if (middle < right) {
            rightRange = composeRange(left, right, right(node), middle + 1, nodeRight);
            if (hasLeftRange) {
                return compose(leftRange, rightRange);
            } else {
                return rightRange;
            }
        } else {
            return leftRange;
        }
    }

    private E compose(E a, E b) {
        return (a == null || b == null) ? null : composer.compose(a, b);
    }

    private static int validCapacity(int capacity) {
        int d = 1;
        while (d < capacity) {
            d = d * 2;
        }
        return d;
    }

    public static interface Composer<E> {
        E compose(E a, E b);
    }

}
