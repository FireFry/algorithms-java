package com.burakovv.data;

public interface BinaryTreeData {

    boolean hasLeft(int node);

    int getLeft(int node);

    boolean hasRight(int node);

    int getRight(int node);

    int compare(int node, Object key);

    boolean hasParent(int node);

    int getParent(int node);

}