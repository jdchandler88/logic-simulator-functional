package com.darchan.logic.simulator.functional.exception;

/**
 * Exception to be thrown if a range is malformed. This happens when a min is greater than max
 */
public class InvalidRangeException extends RuntimeException {

    static final String ERROR_MESSAGE_TEMPLATE = "Min must be less than max. Min was %d and max was %d";

    /**
     * Create invalid range exception with provided min and max
     * @param min minimum rovided
     * @param max maximum provided
     */
    public InvalidRangeException(int min, int max) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, min, max));
    }

}
