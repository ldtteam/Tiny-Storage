package com.timthebrick.tinystorage.common.reference;

public enum ChestSize {
    SMALL(7),
    MEDIUM(14),
    LARGE(21);

    public final int numItems;

    ChestSize(final int numItems) {
        this.numItems = numItems;
    }
}
