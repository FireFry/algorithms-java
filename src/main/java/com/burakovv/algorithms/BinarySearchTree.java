package com.burakovv.algorithms;

public class BinarySearchTree {
    private static final int NIL = -1;

    private final int[] parentArray;
    private final int[] leftArray;
    private final int[] rightArray;
    private final int[] values;

    private int next = 0;
    private int root = NIL;

    public BinarySearchTree(int size) {
        parentArray = new int[size];
        leftArray = new int[size];
        rightArray = new int[size];
        values = new int[size];
    }

    public void inorderTreeWalk(int root, Visitor visitor) {
        if (root == NIL) {
            return;
        }
        inorderTreeWalk(leftArray[root], visitor);
        visitor.visit(this, root);
        inorderTreeWalk(rightArray[root], visitor);
    }

    public int min(int root) {
        int node;
        while ((node = leftArray[root]) != NIL) {
            root = node;
        }
        return root;
    }

    public int max(int root) {
        int node;
        while ((node = rightArray[root]) != NIL) {
            root = node;
        }
        return root;
    }

    public int search(int node, int value) {
        if (node == NIL) {
            return node;
        }
        int compareResult = Integer.compare(value, values[node]);
        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return search(leftArray[node], value);
        } else {
            return search(rightArray[node], value);
        }
    }

    public int successor(int node) {
        int right = rightArray[node];
        if (right != NIL) {
            return min(right);
        }
        int parent = parentArray[node];
        while (parent != NIL && rightArray[parent] == node) {
            node = parent;
            parent = parentArray[parent];
        }
        return node;
    }

    public int predecessor(int node) {
        int left = leftArray[node];
        if (left != NIL) {
            return max(left);
        }
        int parent = parentArray[node];
        while (parent != NIL && leftArray[parent] == node) {
            node = parent;
            parent = parentArray[parent];
        }
        return node;
    }

    public int insert(int value) {
        int node = next++;
        parentArray[node] = leftArray[node] = rightArray[node] = NIL;
        values[node] = value;
        int x = root;
        int p = NIL;
        while (x != NIL) {
            p = x;
            x = (values[x] < value) ? leftArray[x] : rightArray[x];
        }
        parentArray[node] = p;
        if (p == NIL) {
            root = node;
        } else if (value < values[p]) {
            leftArray[p] = node;
            parentArray[node] = p;
        } else {
            rightArray[p] = node;
            parentArray[node] = p;
        }
        return node;
    }

    public int remove(int node) {
        int y = leftArray[node] != NIL && rightArray[node] != NIL ? successor(node) : node;
        int x = leftArray[y];
        if (x == NIL) {
            x = rightArray[y];
        }
        if (x != NIL) {
            parentArray[x] = parentArray[y];
        }
        if (parentArray[y] == NIL) {
            root = x;
        } else if (y == leftArray[parentArray[y]]) {
            leftArray[parentArray[y]] = x;
        } else {
            rightArray[parentArray[y]] = x;
        }
        if (y != node) {
            values[node] = values[y];
        }
        return y;
    }

    public static interface Visitor {
        void visit(BinarySearchTree tree, int node);
    }
}
