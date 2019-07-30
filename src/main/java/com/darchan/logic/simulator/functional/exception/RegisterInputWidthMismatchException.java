package com.darchan.logic.simulator.functional.exception;

/**
 * Exception for reporting when input widths do not match when not expected
 */
public class RegisterInputWidthMismatchException extends RuntimeException {

    /**
     * error message for
     */
    static final String REGISTER_INPUT_WIDTH_MISMATCH_ERROR_MESSAGE = "Register's previous and current input widths must match. previousWidth=%d, currentWidth=%d";

    /**
     * Creates exception with previous and current widths
     * @param previousWidth previous register input width
     * @param currentWidth current register input width
     */
    public RegisterInputWidthMismatchException(int previousWidth, int currentWidth) {
        super(String.format(REGISTER_INPUT_WIDTH_MISMATCH_ERROR_MESSAGE, previousWidth, currentWidth));
    }

}
