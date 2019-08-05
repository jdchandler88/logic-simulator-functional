package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;

/**
 * Class containing validation functions
 */
public final class Validations {

    /**
     * private to avoid instantiation
     */
    private Validations() {}

    /**
     * Validates width of array falls within range, inclusive
     * @param input input to validate
     * @param range range used to validate
     * @return input if it passes validation
     */
    public static Boolean[] validateWidthInRange(Boolean[] input, Range range) {
        Validations.validateNotNull(input);
        Validations.validateNotNull(range);
        if (input.length > range.getMax() || input.length < range.getMin()) {
            throw new RangeValidationException(range);
        }
        return input;
    }

    /**
     * Validates that the input is not null.
     * @param input input to validate
     * @param <T> type of input
     * @return if validated, the input is returned
     */
    public static <T> T validateNotNull(T input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        return input;
    }

}
