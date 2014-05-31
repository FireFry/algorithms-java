package com.burakovv.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest extends Assert {

    @Test
    public void testSearch() {
        Graph graph = new Graph(5);
        graph.connectDirectly(0, 1, 1);
        graph.connectDirectly(0, 2, 1);
        graph.connectDirectly(1, 2, 1);
        graph.connectDirectly(1, 3, 1);
        graph.connectDirectly(3, 4, 1);
        graph.connectDirectly(4, 2, 1);

        TestVisitor visitor = new TestVisitor(new int[]{0}, new int[]{1, 2}, new int[]{3}, new int[]{4});
        graph.breadthFirstSearch(0, visitor);
        visitor.assertFinished();

        visitor = new TestVisitor(new int[]{0, 1, 2, 3, 4});
        graph.depthFirstSearch(0, visitor);
        visitor.assertFinished();
    }

    private class TestVisitor implements Graph.Visitor {
        private final int[][] steps;
        private final int[] sizes;
        private int index;

        public TestVisitor(int[]... steps) {
            this.steps = steps;
            this.sizes = new int[steps.length];
            for (int i = 0; i < sizes.length; i++) {
                sizes[i] = steps[i].length;
            }
            index = 0;
        }

        @Override
        public boolean visit(int node) {
            assertNotEquals(Graph.NIL, node);
            int i = 0;
            while (i < steps[index].length && steps[index][i] != node) {
                i++;
            }
            assertTrue(i < steps[index].length);
            steps[index][i] = Graph.NIL;
            sizes[index]--;
            while (index < sizes.length && sizes[index] == 0) {
                index++;
            }
            return false;
        }

        public void assertFinished() {
            assertFalse(index < sizes.length);
        }
    }
}
