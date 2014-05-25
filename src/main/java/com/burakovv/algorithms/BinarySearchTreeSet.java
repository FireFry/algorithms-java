package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;

import java.util.Comparator;

public class BinarySearchTreeSet<E> implements CustomSet<E> {
    private final Comparator<E> comparator;
    private Node<E> root;

    public BinarySearchTreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(E value) {
        Node<E> y = null;
        Node<E> x = root;
        int lastComparisonResult = -1;
        while (x != null) {
            y = x;
            lastComparisonResult = comparator.compare(value, x.value);
            if (lastComparisonResult == 0) {
                return false;
            }
            x = lastComparisonResult < 0 ? x.left : x.right;
        }
        Node<E> newNode = new Node<E>(value);
        if (y == null) {
            root = newNode;
        } else if (lastComparisonResult < 0) {
            makeLeftChild(y, newNode);
        } else {
            makeRightChild(y, newNode);
        }
        return true;
    }

    @Override
    public boolean remove(Object value) {
        try {
            return removeE((E) value);
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean removeE(E value) {
        Node<E> z = search(value);
        if (z == null) {
            return false;
        }
        Node<E> y = (z.left != null && z.right != null) ? successor(z) : z;
        //y can't have both children by definition (successor can not have left child in this situation)
        Node<E> yChild = (y.left != null) ? y.left : y.right;
        if (yChild != null) {
            yChild.parent = y.parent;
        }
        if (y.parent == null) {
            root = yChild;
        } else if (y.parent.left == y) {
            y.parent.left = yChild;
        } else {
            y.parent.right = yChild;
        }
        if (y != z) {
            z.value = y.value;
        }
        return true;
    }

    @Override
    public boolean contains(E value) {
        return search(value) != null;
    }

    private Node<E> search(E value) {
        Node<E> node = root;
        while (node != null) {
            int compareResult = comparator.compare(value, node.value);
            if (compareResult == 0) {
                return node;
            } else if (compareResult < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private Node<E> successor(Node<E> node) {
        if (node.right != null) {
            return min(node.right);
        }
        Node<E> parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private Node<E> min(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static <T> void makeLeftChild(Node<T> parent, Node<T> leftChild) {
        if (parent.left != null) {
            throw new IllegalArgumentException("Parent already has left child");
        }
        if (leftChild.parent != null) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.left = leftChild;
        leftChild.parent = parent;
    }

    private static <T> void makeRightChild(Node<T> parent, Node<T> rightChild) {
        if (parent.right != null) {
            throw new IllegalArgumentException("Parent already has right child");
        }
        if (rightChild.parent != null) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.right = rightChild;
        rightChild.parent = parent;
    }

    static final class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T value) {
            this.value = value;
        }
    }
}
