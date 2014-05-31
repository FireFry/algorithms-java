package com.burakovv.algorithms;

import java.util.*;

public class AdjListGraph<K, V, E> {
    private final Map<K, Vertex<V, E>> vertices = new HashMap<K, Vertex<V, E>>();

    public V put(K key, V value) {
        Vertex<V, E> vertex = vertices.get(key);
        if (vertex == null) {
            vertices.put(key, new Vertex<V, E>(value));
            return null;
        } else {
            V result = vertex.value;
            vertex.value = value;
            return result;
        }
    }

    public V remove(K key) {
        Vertex<V, E> vertex = vertices.remove(key);
        if (vertex != null) {
            for (Edge<V, E> edge : new ArrayList<Edge<V, E>>(vertex.inwardEdges.values())) {
                edge.from.putEdge(vertex, null);
            }
            return vertex.value;
        }
        return null;
    }

    public V get(K key) {
        Vertex<V, E> vertex = vertices.get(key);
        return vertex == null ? null : vertex.value;
    }

    public E directEdge(K from, K to, E value) {
        return vertices.get(from).putEdge(vertices.get(to), value);
    }

    public E edgeValue(K from, K to) {
        Vertex<V, E> vertex = vertices.get(from);
        if (vertex == null) {
            return null;
        }
        Edge<V, E> edge = vertex.outwardEdges.get(vertices.get(to));
        return edge == null ? null : edge.value;
    }

    public void mutualEdge(K a, K b, E value) {
        directEdge(a, b, value);
        directEdge(b, a, value);
    }

    public E removeDirectEdge(K from, K to) {
        return vertices.get(from).removeEdge(vertices.get(to));
    }

    public void removeMutualEdge(K a, K b) {
        removeDirectEdge(a, b);
        removeDirectEdge(b, a);
    }

    static final class Vertex<V, E> {
        V value;
        final Map<Vertex<V, E>, Edge<V, E>> outwardEdges = new HashMap<Vertex<V, E>, Edge<V, E>>();
        final Map<Vertex<V, E>, Edge<V, E>> inwardEdges = new HashMap<Vertex<V, E>, Edge<V, E>>();

        Vertex(V value) {
            this.value = value;
        }

        public E putEdge(Vertex<V, E> toVertex, E value) {
            Edge<V, E> edge = outwardEdges.get(toVertex);
            if (edge == null) {
                edge = new Edge<V, E>(this, toVertex, value);
                outwardEdges.put(toVertex, edge);
                toVertex.inwardEdges.put(this, edge);
                return null;
            } else {
                E result = edge.value;
                edge.value = value;
                return result;
            }
        }

        public E removeEdge(Vertex<V, E> toVertex) {
            Edge<V, E> edge = outwardEdges.get(toVertex);
            return edge == null ? null : edge.value;
        }
    }

    static final class Edge<V, E> {
        final Vertex<V, E> from;
        final Vertex<V, E> to;
        E value;

        Edge(Vertex<V, E> from, Vertex<V, E> to, E value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
