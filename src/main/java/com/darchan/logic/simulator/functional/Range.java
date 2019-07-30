package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.InvalidRangeException;

/**
 * Class representing a range [min, max]
 */
public class Range {

    /**
     * range minimum
     */
    private final int min;

    /**
     * range maximum
     */
    private final int max;

    /**
     * Creates range with minimum and maximum
     * @param min range minimum
     * @param max range maximum
     * @throws  InvalidRangeException if min is greater than max
     */
    public Range(int min, int max) {
        if (min > max) {
            throw new InvalidRangeException(min, max);
        }
        this.min = min;
        this.max = max;
    }

    /**
     * Gets range minimum
     * @return minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * Gets range maximum
     * @return range maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * String representation of range
     * @return string representation of range
     */
    @Override
    public String toString() {
        return "[" + min + "," + max + "]";
    }
}
