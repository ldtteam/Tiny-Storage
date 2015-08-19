package com.timthebrick.tinystorage.util.math;

public class ArrayHelper {
    /**
     * @param startVal  The value to start at
     * @param endVal    The value to end at
     * @param inclusive Whether or not to include the start and end values
     * @return Eg fillIntArray(0, 10, true) returns an array with the values 0 through 10, fillIntArray(0, 10, false) returns an array with the values 1 through 9
     */
    public static int[] fillIntArray(int startVal, int endVal, boolean inclusive) {
        if (startVal > endVal) {
            return new int[]{0};
        }
        if (inclusive) {
            int[] array = new int[endVal - startVal + 1];
            for (int i = startVal; i <= endVal; i++) {
                array[i - startVal] = i;
            }
            return array;
        } else {
            int[] array = new int[endVal - startVal - 1];
            for (int i = startVal + 1; i < endVal; i++) {
                array[i - startVal - 1] = i;
            }
            return array;
        }
    }
}
