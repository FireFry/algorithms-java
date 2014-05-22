package com.burakovv.algorithms;

import org.junit.Test;

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
}
