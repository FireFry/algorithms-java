package com.burakovv.data;

/**
 * Provides access to abstract ordered data. Designed to hide actual data type. Such representation allows to manipulate with
 * collection of elements without having actual references to any of them. It makes possible to create implementations with Objects
 * as well as with primitive types omitting generalization and useless autoboxing overhead. There are a lot of algorithms where storing
 * reference to element is necessary. There is a special area called "buffer" for this purpose.
 *
 * <pre>
 * +---------------------------+---------+
 * |            Data           | Buffer  |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * | | | | | | | | | | | | | | | | | | | |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * </pre>
 */
public interface OrderedData {

    /**
     * @return the index of the first element
     */
    int getOffset();

    /**
     * @return the number of data elements
     */
    int getSize();

    /**
     * @return the index of first buffer cell
     */
    int getBufferOffset();

    /**
     * @return the size of the buffer
     */
    int getBufferSize();

    /**
     * Simultaneously moves element from cell {@code i} to cell {@code j} and element from cell {@code j} to cell {@code i}
     */
    void swap(int i, int j);

}
