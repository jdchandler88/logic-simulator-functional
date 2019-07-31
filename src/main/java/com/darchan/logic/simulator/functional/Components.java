package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.EncoderDecoderInputWidthException;
import com.darchan.logic.simulator.functional.exception.InvalidEncoderInputValueException;
import com.darchan.logic.simulator.functional.exception.RegisterInputWidthMismatchException;
import com.darchan.logic.simulator.functional.exception.RangeValidationException;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class Components {

    /**
     * valid input range for and gate
     */
    private static final Range AND_INPUT_RANGE = new Range(2, Integer.MAX_VALUE);

    /**
     * valid input range for a register
     */
    private static final Range REGISTER_INPUT_RANGE = new Range(1, Integer.MAX_VALUE);

    /**
     * valid input range for encoder/decoder modules
     */
    private static final Range ENCODER_DECODER_INPUT_RANGE = new Range(2, Integer.MAX_VALUE);


    private Components() {}

    /**
     * And gate function
     * @param input input signals
     * @return evaluation of inputs. True if ALL inputs are true, false otherwise
     * @throws IllegalArgumentException if input is null
     * @throws RangeValidationException if input width is less than 2
     */
    public static boolean and(boolean[] input) {
        validateNullAndWidth(input, AND_INPUT_RANGE);
        return IntStream.range(0, input.length)
                .mapToObj(i -> input[i])
                .allMatch(signal -> signal);
    }

    /**
     * Register function.
     * @param previousClock value of previous clock
     * @param currentClock value of current clock
     * @param previousInput value of previous input
     * @param currentInput value of current input
     * @return new output. will be 'currentInput' if the clock has risen and will be 'previousInput' otherwise
     * @throws IllegalArgumentException if previousInput or currentInput is null
     * @throws RangeValidationException if previousInput or currentInput are 0-length
     * @throws RegisterInputWidthMismatchException if previousInput and currentInput lengths differ
     */
    public static boolean[] register(boolean previousClock, boolean currentClock, boolean[] previousInput, boolean[] currentInput) {
        //validate inputs: not null, width, and width match
        validateNullAndWidth(previousInput, REGISTER_INPUT_RANGE);
        validateNullAndWidth(currentInput, REGISTER_INPUT_RANGE);
        if (previousInput.length != currentInput.length) {
            throw new RegisterInputWidthMismatchException(previousInput.length, currentInput.length);
        }

        //only latch values on positive edge of clock
        if (!previousClock && currentClock) {
            return currentInput;
        } else {
            return previousInput;
        }
    }

    /**
     * Encode an array of booleans into a binary number. For instance, a truth table for a 4-bit array is as follows
     *
     * 0001 -- 00
     * 0010 -- 01
     * 0100 -- 10
     * 1000 -- 11
     *
     * Note that inputs should be widths of powers of 2 (2, 4, 8, ...)
     *
     * @param toEncode array to encode
     * @return encoded binary number
     */
    public static boolean[] encode(boolean[] toEncode) {
        validateNullAndWidth(toEncode, ENCODER_DECODER_INPUT_RANGE);
        //input width must be power of two
        if (toEncode.length < 2 || !isPowerOfTwo(toEncode.length)) {
            throw new EncoderDecoderInputWidthException(toEncode.length);
        }
        //one and only one input should be on
        if (IntStream.range(0, toEncode.length)
                .filter(i -> toEncode[i])
                .count() != 1) {
            throw new InvalidEncoderInputValueException(toEncode);
        }
        //which bit is on?
        int selectedIdx = IntStream.range(0, toEncode.length)
            .filter(i -> toEncode[i])
            .findFirst()
            .getAsInt();

        //calculate the width of the encoded signal
        int power = 0;
        while (1 << power != toEncode.length) {
            power++;
        }

        //use the width to create the signal and calculate which signals are on
        int encodedWidth = power;
        boolean[] encoded = new boolean[encodedWidth];
        for (int i=0; i<encodedWidth; i++) {
            encoded[i] = ((1 << i) & selectedIdx) > 0;
        }

        return encoded;
    }

    public static boolean[] decode(boolean[] toDecode) {
        return null;
    }

    private static boolean[] validateNullAndWidth(boolean[] input, Range range) {
        return Validations.validateWidthInRange(Validations.validateNotNull(input), range);
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && Integer.bitCount(number) == 1;
    }

}
