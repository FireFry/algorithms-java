package com.burakovv.data.impl;

import com.burakovv.data.ComparableData;

public class IntArrayDataWrapper implements ComparableData {
    private final int[] data;
    private final int offset;
    private final int size;
    private final int bufferOffset;
    private final int bufferSize;

    public IntArrayDataWrapper(int[] data, int offset, int size, int bufferOffset, int bufferSize) {
        this.data = data;
        this.offset = offset;
        this.size = size;
        this.bufferOffset = bufferOffset;
        this.bufferSize = bufferSize;
    }

    @Override
    public int compare(int i, int j) {
        return Integer.compare(data[i], data[j]);
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getBufferOffset() {
        return bufferOffset;
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    @Override
    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void set(int index, int value) {
        data[index] = value;
    }
}
