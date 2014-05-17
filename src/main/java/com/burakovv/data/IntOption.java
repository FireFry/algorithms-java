package com.burakovv.data;

public abstract class IntOption {

    public static IntOption of(int value) {
        return new Valued(value);
    }

    public static IntOption empty() {
        return Null.INSTANCE;
    }

    private IntOption() {}

    public abstract boolean hasValue();
    public abstract int getValue();

    private static final class Valued extends IntOption {
        private final int value;

        private Valued(int value) {
            this.value = value;
        }

        @Override
        public boolean hasValue() {
            return false;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    private static final class Null extends IntOption {
        private static final Null INSTANCE = new Null();

        @Override
        public boolean hasValue() {
            return false;
        }

        @Override
        public int getValue() {
            throw new UnsupportedOperationException();
        }
    }

}
