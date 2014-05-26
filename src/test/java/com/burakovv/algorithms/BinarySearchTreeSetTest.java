package com.burakovv.algorithms;

import com.burakovv.data.CustomSet;

import java.util.Comparator;

public class BinarySearchTreeSetTest extends CustomSetTest {

    @Override
    protected <T> CustomSet<T> createCustomSet(Comparator<T> comparator) {
        return new CustomSetFromMap<T>(new BinarySearchTreeMap<T, Object>(comparator));
    }

}
