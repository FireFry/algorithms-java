package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;

import java.util.Comparator;

public class RedBlackTreeSet<E> implements CustomSet<E> {
    private static Node NIL = createNil();

    private static Node createNil() {
        Node node = new Node(null);
        node.color = Color.BLACK;
        return node;
    }

    private final Comparator<E> comparator;
    private Node<E> root = NIL;

    public RedBlackTreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(E value) {
        Node<E> y = NIL;
        Node<E> x = root;
        int lastComparisonResult = -1;
        while (x != NIL) {
            y = x;
            lastComparisonResult = comparator.compare(value, x.value);
            if (lastComparisonResult == 0) {
                return false;
            }
            x = lastComparisonResult < 0 ? x.left : x.right;
        }
        Node<E> newNode = new Node<E>(value);
        if (y == NIL) {
            root = newNode;
        } else if (lastComparisonResult < 0) {
            makeLeftChild(y, newNode);
        } else {
            makeRightChild(y, newNode);
        }
        newNode.color = Color.RED;
        addFixup(newNode);
        return true;
    }

    private void addFixup(Node<E> z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                if (z.parent.parent.right.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    z = z.parent.parent;
                    z.right.color = Color.BLACK;
                    z.color = Color.RED;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                if (z.parent.parent.left.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    z = z.parent.parent;
                    z.left.color = Color.BLACK;
                    z.color = Color.RED;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
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
        if (z == NIL) {
            return false;
        }
        Node<E> y = (z.left != NIL && z.right != NIL) ? successor(z) : z;
        //y can't have both children by definition (successor can not have left child in this situation)
        Node<E> yChild = (y.left != NIL) ? y.left : y.right;
        yChild.parent = y.parent; //Even if yChild is NIL
        if (y.parent == NIL) {
            root = yChild;
        } else if (y.parent.left == y) {
            y.parent.left = yChild;
        } else {
            y.parent.right = yChild;
        }
        if (y != z) {
            z.value = y.value;
        }
        if (y.color == Color.BLACK) {
            removeFixup(yChild);
        }
        return true;
    }

    private void removeFixup(Node<E> x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node<E> w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    //w is black, w.right is red
                    w.color = x.parent.color;
                    x.parent.color = w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node<E> w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    //w is black, w.left is red
                    w.color = x.parent.color;
                    x.parent.color = w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    @Override
    public boolean contains(E value) {
        return search(value) != NIL;
    }

    private Node<E> search(E value) {
        Node<E> node = root;
        while (node != NIL) {
            int compareResult = comparator.compare(value, node.value);
            if (compareResult == 0) {
                return node;
            } else if (compareResult < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return NIL;
    }

    private Node<E> successor(Node<E> node) {
        if (node.right != NIL) {
            return min(node.right);
        }
        Node<E> parent = node.parent;
        while (parent != NIL && parent.right == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private Node<E> min(Node<E> node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    private static <T> void makeLeftChild(Node<T> parent, Node<T> leftChild) {
        if (parent.left != NIL) {
            throw new IllegalArgumentException("Parent already has left child");
        }
        if (leftChild.parent != NIL) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.left = leftChild;
        leftChild.parent = parent;
    }

    private static <T> void makeRightChild(Node<T> parent, Node<T> rightChild) {
        if (parent.right != NIL) {
            throw new IllegalArgumentException("Parent already has right child");
        }
        if (rightChild.parent != NIL) {
            throw new IllegalArgumentException("Child already has a parent");
        }
        parent.right = rightChild;
        rightChild.parent = parent;
    }

    private void leftRotate(Node<E> x) {
        Node<E> y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            root = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        x.parent = y;
        y.left = x;
    }

    private void rightRotate(Node<E> y) {
        Node<E> x = y.left;
        y.left = x.right;
        if (y.left != NIL) {
            y.left.parent = y;
        }
        x.right = y;
        x.parent = y.parent;
        if (y.parent == NIL) {
            root = x;
        } else if (y.parent.left == y) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        y.parent = x;
    }

    public void assertCorresct() {
        if (root.color == Color.RED) {
            throw new RuntimeException("The root is red");
        }
        findBlacks(root, 0);
    }

    private int findBlacks(Node<E> node, int current) {
        if (node.color == Color.BLACK) {
            current++;
        }
        if (node == NIL) {
            return current;
        }
        if (node.color == Color.RED && (node.left.color == Color.RED || node.right.color == Color.RED)) {
            throw new RuntimeException("Red has red child");
        }
        int leftBlacks = findBlacks(node.left, current);
        int rightBlacks = findBlacks(node.right, current);
        if (leftBlacks != rightBlacks) {
            throw new RuntimeException("Tree is not balanced");
        }
        return leftBlacks;
    }

    public boolean isBalanced() {
        int minWay = minWayLength(root, 0);
        int maxWay = maxWayLength(root, 0);
        return 2 * minWay >= maxWay;
    }

    private int minWayLength(Node<E> node, int current) {
        return node == NIL ? current : Math.min(
                minWayLength(node.left, current + 1),
                minWayLength(node.right, current + 1));
    }

    private int maxWayLength(Node<E> node, int current) {
        return node == NIL ? current : Math.max(
                maxWayLength(node.left, current + 1),
                maxWayLength(node.right, current + 1));
    }

    static final class Node<T> {
        T value;
        Node<T> left = NIL;
        Node<T> right = NIL;
        Node<T> parent = NIL;
        Color color;

        public Node(T value) {
            this.value = value;
        }
    }

    static enum Color {
        BLACK,
        RED,
    }
}
