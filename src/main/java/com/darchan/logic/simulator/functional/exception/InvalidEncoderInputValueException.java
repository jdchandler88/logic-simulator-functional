package com.darchan.logic.simulator.functional.exception;

import java.util.Arrays;

/**
 * Exception thrown if there is not EXACTLY ONE input on for an encoder
 */
public class InvalidEncoderInputValueException extends RuntimeException {

    /**
     * error template for exception
     */
    static final String ERROR_MESSAGE_TEMPLATE = "Only one bit for encoder input should be on. Received input was: %s";

    /**
     * Creates exception with invalid input
     * @param input invalid input
     */
    public InvalidEncoderInputValueException(boolean[] input) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, Arrays.toString(input)));
    }

}
