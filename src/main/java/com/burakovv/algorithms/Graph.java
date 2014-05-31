package com.burakovv.algorithms;

import java.util.Arrays;

public class Graph {
    public static final int NIL = Integer.MIN_VALUE;

    private final int[] a;
    private int size;

    private final int[] queue;
    private final boolean[] marks;

    public Graph(int size) {
        this.size = size;
        this.queue = new int[size];
        this.marks = new boolean[size];
        this.a = new int[size * size];
        Arrays.fill(a, NIL);
    }

    public void connectDirectly(int i, int j, int edgeValue) {
        a[indexOf(i, j)] = edgeValue;
    }

    public void connect(int i, int j, int edgeValue) {
        connectDirectly(i, j, edgeValue);
        connectDirectly(j, i, edgeValue);
    }

    public void breadthFirstSearch(int node, Visitor visitor) {
        Arrays.fill(marks, false);
        marks[node] = true;
        queue[0] = node;
        int n = 1;
        int i = 0;
        while (i < n) {
            int visitNode = queue[i++];
            if (visitor.visit(visitNode)) {
                return;
            }
            for (int j = 0; j < size; j++) {
                if (!marks[j] && a[indexOf(visitNode, j)] != NIL) {
                    marks[j] = true;
                    queue[n++] = j;
                }
            }
        }
    }

    public void depthFirstSearch(int node, Visitor visitor) {
        Arrays.fill(marks, false);
        marks[node] = true;
        depthFirstSearchStep(node, visitor);
    }

    private boolean depthFirstSearchStep(int node, Visitor visitor) {
        if (visitor.visit(node)) {
            return true;
        }
        for (int j = 0; j < size; j++) {
            if (!marks[j] && a[indexOf(node, j)] != NIL) {
                marks[j] = true;
                if (depthFirstSearchStep(j, visitor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int indexOf(int i, int j) {
        return i * size + j;
    }

    public static interface Visitor {
        boolean visit(int node);
    }
}
