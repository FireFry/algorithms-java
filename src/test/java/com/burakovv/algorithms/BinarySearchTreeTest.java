package com.burakovv.algorithms;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.burakovv.algorithms.BinarySearchTree.*;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void testTree0() {
        BinarySearchTree tree = create(
                new int[] { 3 },
                new int[][] {
                        { NIL, NIL },
                }, 0
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(0));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(0, tree.min(0));
        assertEquals(0, tree.max(0));
        assertEquals(NIL, tree.successor(0));
        assertEquals(NIL, tree.predecessor(0));
        assertEquals(0, tree.remove(0));
    }

    @Test
    public void testTree1() {
        BinarySearchTree tree = create(
                new int[] { 2, 1 },
                new int[][] {
                        { NIL, NIL },
                        { NIL, 0 },
                }, 1
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(0, tree.search(tree.getRoot(), 2));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(0, tree.max(tree.getRoot()));
        assertEquals(NIL, tree.successor(0));
        assertEquals(1, tree.predecessor(0));
        assertEquals(0, tree.successor(1));
        assertEquals(NIL, tree.predecessor(1));
    }

    @Test
    public void testTree2() {
        BinarySearchTree tree = create(
                new int[] { 3, 1, 4 },
                new int[][] {
                        { 1, NIL },
                        { NIL, NIL },
                        { 0, NIL },
                }, 2
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0, 2));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(2, tree.search(tree.getRoot(), 4));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(2, tree.max(tree.getRoot()));
        assertEquals(1, tree.predecessor(0));
        assertEquals(2, tree.successor(0));
        assertEquals(NIL, tree.predecessor(1));
        assertEquals(0, tree.successor(1));
        assertEquals(0, tree.predecessor(2));
        assertEquals(NIL, tree.successor(2));
    }

    @Test
    public void testTree3() {
        BinarySearchTree tree = create(
                new int[] { 3, 1, 4 },
                new int[][] {
                        { NIL, NIL },
                        { NIL, 0 },
                        { 1, NIL },
                }, 2
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0, 2));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(2, tree.search(tree.getRoot(), 4));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(2, tree.max(tree.getRoot()));
        assertEquals(1, tree.predecessor(0));
        assertEquals(2, tree.successor(0));
        assertEquals(NIL, tree.predecessor(1));
        assertEquals(0, tree.successor(1));
        assertEquals(0, tree.predecessor(2));
        assertEquals(NIL, tree.successor(2));
    }

    @Test
    public void testTree4() {
        BinarySearchTree tree = create(
                new int[] { 3, 1, 4 },
                new int[][] {
                        { 1, 2 },
                        { NIL, NIL },
                        { NIL, NIL },
                }, 0
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0, 2));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(2, tree.search(tree.getRoot(), 4));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(2, tree.max(tree.getRoot()));
        assertEquals(1, tree.predecessor(0));
        assertEquals(2, tree.successor(0));
        assertEquals(NIL, tree.predecessor(1));
        assertEquals(0, tree.successor(1));
        assertEquals(0, tree.predecessor(2));
        assertEquals(NIL, tree.successor(2));
    }

    @Test
    public void testTree5() {
        BinarySearchTree tree = create(
                new int[] { 3, 1, 4 },
                new int[][] {
                        { NIL, NIL },
                        { NIL, 2 },
                        { 0, NIL },
                }, 1
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0, 2));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(2, tree.search(tree.getRoot(), 4));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(2, tree.max(tree.getRoot()));
        assertEquals(1, tree.predecessor(0));
        assertEquals(2, tree.successor(0));
        assertEquals(NIL, tree.predecessor(1));
        assertEquals(0, tree.successor(1));
        assertEquals(0, tree.predecessor(2));
        assertEquals(NIL, tree.successor(2));
    }

    @Test
    public void testTree6() {
        BinarySearchTree tree = create(
                new int[] { 3, 1, 4 },
                new int[][] {
                        { NIL, 2 },
                        { NIL, 0 },
                        { NIL, NIL },
                }, 1
        );
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(1, 0, 2));
        assertEquals(0, tree.search(tree.getRoot(), 3));
        assertEquals(1, tree.search(tree.getRoot(), 1));
        assertEquals(2, tree.search(tree.getRoot(), 4));
        assertEquals(1, tree.min(tree.getRoot()));
        assertEquals(2, tree.max(tree.getRoot()));
        assertEquals(1, tree.predecessor(0));
        assertEquals(2, tree.successor(0));
        assertEquals(NIL, tree.predecessor(1));
        assertEquals(0, tree.successor(1));
        assertEquals(0, tree.predecessor(2));
        assertEquals(NIL, tree.successor(2));
    }

    @Test
    public void testRemove() {
        BinarySearchTree tree = create(
                new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                new int[][] {
                        { NIL, 1 },
                        { NIL, 3 },
                        { NIL, NIL },
                        { 2, NIL },
                        { 0, 9 },
                        { NIL, 7 },
                        { NIL, NIL },
                        { 6, 8 },
                        { NIL, NIL },
                        { 5, NIL },
                }, 4
        );
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        tree.remove(tree.search(tree.getRoot(), 1+4));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(0, 1, 2, 3, 5, 6, 7, 8, 9));
        tree.remove(tree.search(tree.getRoot(), 1+2));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(0, 1, 3, 5, 6, 7, 8, 9));
        tree.remove(tree.search(tree.getRoot(), 1+9));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(0, 1, 3, 5, 6, 7, 8));
        tree.remove(tree.search(tree.getRoot(), 1+0));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(1, 3, 5, 6, 7, 8));
        tree.remove(tree.search(tree.getRoot(), 1+7));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(1, 3, 5, 6, 8));
        tree.remove(tree.search(tree.getRoot(), 1+3));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(1, 5, 6, 8));
        tree.remove(tree.search(tree.getRoot(), 1+1));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(5, 6, 8));
        tree.remove(tree.search(tree.getRoot(), 1+8));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(5, 6));
        tree.remove(tree.search(tree.getRoot(), 1+6));
        tree.inorderTreeWalk(tree.getRoot(), new RemoveTestVisitor(5));
    }

    @Test
    public void testInsert() {
        BinarySearchTree tree = new BinarySearchTree(10);
        tree.inorderTreeWalk(tree.getRoot(), new TestVisitor());
        Random random = new Random(42);
        ArrayList<Integer> list = new ArrayList<Integer>(10);
        ArrayList<Integer> control = new ArrayList<Integer>(10);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list, random);
        for (int i = 0; i < 10; i++) {
            int val = list.get(i);
            tree.insert(val);
            control.add(val);
            Collections.sort(control);
            int[] array = new int[control.size()];
            for (int j = 0; j < control.size(); j++) {
                array[j] = control.get(j);
            }
            tree.inorderTreeWalk(tree.getRoot(), new TestVisitor(array) {
                @Override
                public void visit(BinarySearchTree tree, int node) {
                    super.visit(tree, tree.getValue(node));
                }
            });
        }
    }

    private class TestVisitor implements Visitor {
        private final int[] vertexes;
        private int next;

        public TestVisitor(int... vertexes) {
            this.vertexes = vertexes;
            this.next = 0;
        }

        @Override
        public void visit(BinarySearchTree tree, int node) {
            assertEquals(vertexes[next], node);
            next++;
        }
    }

    private class RemoveTestVisitor extends TestVisitor {
        public RemoveTestVisitor(int... vertexes) {
            super(vertexes);
        }

        @Override
        public void visit(BinarySearchTree tree, int node) {
            super.visit(tree, tree.getValue(node) - 1);
        }
    }
}
