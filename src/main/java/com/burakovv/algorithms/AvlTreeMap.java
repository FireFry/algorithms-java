package com.burakovv.algorithms;

import com.burakovv.data.CustomMap;

import java.util.Comparator;

public class AvlTreeMap<K, V> implements CustomMap<K, V> {
    private final Comparator<K> comparator;
    Node<K, V> root;

    public AvlTreeMap() {
        this.comparator = null;
    }

    public AvlTreeMap(Comparator<K> comparator) {
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
        balance(newNode);
        return null;
    }

    protected void balance(Node node) {
        while (node != null) {
            updateHeight(node);
            int nodeFactor = factor(node);
            if (nodeFactor == 2) {
                if (factor(node.left) == -1) {
                    leftRotate(node.left);
                }
                rightRotate(node);
                node = node.parent.parent;
            } else if (nodeFactor == -2) {
                if (factor(node.right) == 1) {
                    rightRotate(node.right);
                }
                leftRotate(node);
                node = node.parent.parent;
            } else {
                node = node.parent;
            }
        }
    }

    private int factor(Node node) {
        return heightOf(node.left) - heightOf(node.right);
    }

    private static int heightOf(Node node) {
        return node == null ? 0 : node.height;
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
            z.value = y.value;
        }
        balance(y.parent);
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

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        x.parent = y;
        y.left = x;
        updateHeight(x);
        updateHeight(y);
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (y.left != null) {
            y.left.parent = y;
        }
        x.right = y;
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y.parent.left == y) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        y.parent = x;
        updateHeight(y);
        updateHeight(x);
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(heightOf(node.left), heightOf(node.right));
    }

    static final class Node<T, V> {
        Node<T, V> left;
        Node<T, V> right;
        Node<T, V> parent;
        T key;
        V value;
        int height;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
