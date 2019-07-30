package com.darchan.logic.simulator.functional.exception;

import com.darchan.logic.simulator.functional.Range;
import com.darchan.logic.simulator.functional.Validations;

/**
 * Exception that is to be thrown when something falls within invalid range
 */
public class RangeValidationException extends RuntimeException {

    /**
     * the range that was violated
     */
    private final Range range;

    /**
     * Create invalid range exception with the supplied range
     * @param range range
     */
    public RangeValidationException(Range range) {
        super("Range must be: " + Validations.validateNotNull(range).toString());
        this.range = range;
    }

    /**
     * Get the range
     * @return range
     */
    public Range getRange() {
        return range;
    }

}
