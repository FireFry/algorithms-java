package com.burakovv.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows to perform function on range of predefined sequence of elements by the time of O(1) (and O(n * log(n)) preprocessing time)
 */
public class SparseTableRMQ<E> {
    private final Function<E> function;
    private final E[][] a;

    public static <E> Builder<E> newBuilder(Function<E> function) {
        return new Builder<E>(function);
    }

    private SparseTableRMQ(Function<E> function, List<E> list) {
        this.function = function;
        int m = getArraysCount(list.size());
        a = (E[][]) new Object[m][];
        a[0] = (E[]) list.toArray();
        int i = 1;
        int d = 1;
        int n = list.size() - d;
        while (n > 0) {
            a[i] = (E[]) new Object[n];
            for (int j = 0; j < n; j++) {
                a[i][j] = function.combine(a[i - 1][j], a[i - 1][j + d]);
            }
            d *= 2;
            n -= d;
            i++;
        }
    }

    private int getArraysCount(int n) {
        int d = 1;
        int total = 1;
        while (n > 0) {
            n = n - d;
            d *= 2;
            total++;
        }
        return total;
    }

    public E composeRange(int left, int right) {
        int diff = right - left + 1;
        int i = 0;
        int d = 1;
        while (d * 2 <= diff) {
            d *= 2;
            i++;
        }
        return function.combine(a[i][left], a[i][right - d + 1]);
    }

    public static class Builder<E> {
        private final ArrayList<E> list = new ArrayList<E>();
        private Function<E> function;

        private Builder(Function<E> function) {
            this.function = function;
        }

        public Builder<E> add(E value) {
            list.add(value);
            return this;
        }

        public Builder<E> add(E... values) {
            for (int i = 0; i < values.length; i++) {
                list.add(values[i]);
            }
            return this;
        }

        public SparseTableRMQ<E> build() {
            return new SparseTableRMQ<E>(function, list);
        }
    }

    public interface Function<E> {
        E combine(E a, E b);
    }
}
