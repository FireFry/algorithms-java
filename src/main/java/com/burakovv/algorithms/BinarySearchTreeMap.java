package com.burakovv.algorithms;

import com.burakovv.data.CustomMap;

import java.util.Comparator;

public class BinarySearchTreeMap<K, V> implements CustomMap<K, V> {
    private final Comparator<K> comparator;
    private Node<K, V> root;

    public BinarySearchTreeMap() {
        this.comparator = null;
    }

    public BinarySearchTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> y = null;
        Node<K, V> x = root;
        int lastComparisonResult = -1;
        while (x != null) {
            y = x;
            lastComparisonResult = compare(key, x.key);
            if (lastComparisonResult == 0) {
                V result = x.value;
                x.value = value;
                return result;
            }
            x = lastComparisonResult < 0 ? x.left : x.right;
        }
        Node<K, V> newNode = new Node<K, V>(key, value);
        if (y == null) {
            root = newNode;
        } else if (lastComparisonResult < 0) {
            makeLeftChild(y, newNode);
        } else {
            makeRightChild(y, newNode);
        }
        return null;
    }

    protected int compare(K a, K b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        }
        return ((Comparable) a).compareTo(b);
    }

    @Override
    public V remove(Object key) {
        try {
            return removeK((K) key);
        } catch (ClassCastException e) {
            return null;
        }
    }

    private V removeK(K key) {
        Node<K, V> z = search(key);
        if (z == null) {
            return null;
        }
        V result = z.value;
        Node<K, V> y = (z.left != null && z.right != null) ? successor(z) : z;
        //y can't have both children by definition (successor can not have left child in this situation)
        Node<K, V> yChild = (y.left != null) ? y.left : y.right;
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
            z.key = y.key;
        }
        return result;
    }

    @Override
    public V get(Object key) {
        try {
            Node<K, V> node = search((K) key);
            return node == null ? null : node.value;
        } catch (ClassCastException e) {
            return null;
        }
    }

    private Node<K, V> search(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int compareResult = compare(key, node.key);
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

    private Node<K, V> successor(Node<K, V> node) {
        if (node.right != null) {
            return min(node.right);
        }
        Node<K, V> parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static <T, V> void makeLeftChild(Node<T, V> parent, Node<T, V> leftChild) {
        if (parent.left != null) {
            throw new IllegalArgumentException("Parent already has left child");
        }
        if (leftChild.parent != null) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.left = leftChild;
        leftChild.parent = parent;
    }

    private static <T, V> void makeRightChild(Node<T, V> parent, Node<T, V> rightChild) {
        if (parent.right != null) {
            throw new IllegalArgumentException("Parent already has right child");
        }
        if (rightChild.parent != null) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.right = rightChild;
        rightChild.parent = parent;
    }

    static final class Node<T, V> {
        Node<T, V> left;
        Node<T, V> right;
        Node<T, V> parent;
        T key;
        V value;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
