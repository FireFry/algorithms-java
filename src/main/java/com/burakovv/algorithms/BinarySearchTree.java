package com.burakovv.algorithms;

import com.burakovv.data.BinaryTreeData;
import com.burakovv.data.IntOption;
import com.burakovv.data.Visiter;

public class BinarySearchTree {

    public static void inorderTreeWalk(BinaryTreeData tree, Visiter visiter, int root) {
        if (tree.hasLeft(root)) {
            inorderTreeWalk(tree, visiter, tree.getLeft(root));
        }
        visiter.visit(tree, root);
        if (tree.hasRight(root)) {
            inorderTreeWalk(tree, visiter, tree.getRight(root));
        }
    }
    
    public static IntOption search(BinaryTreeData tree, Object key, int root) {
        //Can be rewritten iteratively
        int compareResult = tree.compare(root, key);
        if (compareResult == 0) {
            return IntOption.of(root);
        } else if (compareResult > 0) {
            if (tree.hasLeft(root)) {
                return search(tree, key, tree.getLeft(root));
            }
        } else {
            if (tree.hasRight(root)) {
                return search(tree, key, tree.getRight(root));
            }
        }
        return IntOption.empty();
    }

    public static int treeMinimum(BinaryTreeData tree, int root) {
        while (tree.hasRight(root)) {
            root = tree.getRight(root);
        }
        return root;
    }

    public static int treeMaximum(BinaryTreeData tree, int root) {
        while (tree.hasLeft(root)) {
            root = tree.getLeft(root);
        }
        return root;
    }

    public static IntOption treeSuccessor(BinaryTreeData tree, int node) {
        if (tree.hasRight(node)) {
            return IntOption.of(treeMinimum(tree, tree.getRight(node)));
        }
        if (!tree.hasParent(node)) {
            return IntOption.empty();
        }
        int parent = tree.getParent(node);
        while (tree.hasRight(parent) && tree.getRight(parent) == node) {
            if (!tree.hasParent(parent)) {
                return IntOption.empty();
            }
            node = parent;
            parent = tree.getParent(parent);
        }
        return IntOption.of(parent);
    }

    public static IntOption treePredecessor(BinaryTreeData tree, int node) {
        if (tree.hasLeft(node)) {
            return IntOption.of(treeMaximum(tree, tree.getLeft(node)));
        }
        if (!tree.hasParent(node)) {
            return IntOption.empty();
        }
        int parent = tree.getParent(node);
        while (tree.hasLeft(parent) && tree.getLeft(parent) == node) {
            if (!tree.hasParent(parent)) {
                return IntOption.empty();
            }
            node = parent;
            parent = tree.getParent(parent);
        }
        return IntOption.of(parent);
    }

}
