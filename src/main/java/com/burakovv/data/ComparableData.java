package com.burakovv.data;

/**
 * Introduces {@code compare()} method for OrderedData elements
 */
public interface ComparableData extends OrderedData {

    /**
     * @return result of comparing element in cell {@code i} with element in cell {@code j}
     */
    int compare(int i, int j);

}
