package com.darchan.logic.simulator.functional.exception;

/**
 * Exception that is thrown when an encoder or decoder is given an input that does not have a width that is a power of
 * two.
 */
public class EncoderInputWidthException extends RuntimeException {

    /**
     * error message template used for exception message
     */
    static final String ERROR_MESSAGE_TEMPLATE = "Encoder/Decoder must have input width that is a power of two. Input width is: %d";

    /**
     * Creates exception using input width
     * @param inputWidth input width received by encoder/decoder
     */
    public EncoderInputWidthException(int inputWidth) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, inputWidth));
    }

}
