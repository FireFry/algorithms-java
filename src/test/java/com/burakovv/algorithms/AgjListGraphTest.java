package com.burakovv.algorithms;

import junit.framework.TestCase;
import org.junit.Test;

public class AgjListGraphTest extends TestCase {

    @Test
    public void testBuild() {
        AdjListGraph<Integer, Character, Integer> graph = new AdjListGraph<Integer, Character, Integer>();
        assertNull(graph.put(0, 'a'));
        assertNull(graph.put(1, 'b'));
        assertNull(graph.put(2, 'd'));
        assertNull(graph.put(10, 'a'));
        assertEquals(Character.valueOf('b'), graph.remove(1));
        assertNull(graph.put(1, 'b'));
        assertEquals(Character.valueOf('d'), graph.put(2, 'f'));
        assertEquals(Character.valueOf('f'), graph.put(2, 'c'));

        assertNull(graph.directEdge(0, 1, 1));
        assertNull(graph.directEdge(0, 2, 1));
        assertNull(graph.directEdge(1, 2, 1));
        assertNull(graph.directEdge(0, 10, 3));
        assertNull(graph.directEdge(1, 10, 4));
        assertNull(graph.directEdge(10, 2, 5));

        assertEquals((Integer) 3, graph.edgeValue(0, 10));
        assertEquals((Integer) 4, graph.edgeValue(1, 10));
        assertEquals((Integer) 5, graph.edgeValue(10, 2));

        assertEquals((Character) 'a', graph.remove(10));

        assertEquals((Integer) null, graph.edgeValue(0, 10));
        assertEquals((Integer) null, graph.edgeValue(1, 10));
        assertEquals((Integer) null, graph.edgeValue(10, 2));
    }
}
