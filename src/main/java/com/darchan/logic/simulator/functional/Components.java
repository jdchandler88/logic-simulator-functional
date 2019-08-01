package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.EncoderInputWidthException;
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
     * valid input range for an or gate
     */
    private static final Range OR_INPUT_RANGE = new Range(2, Integer.MAX_VALUE);

    /**
     * valid input range for an xor gate
     */
    private static final Range XOR_INPUT_RANGE = new Range(2, 2);

    /**
     * valid input range for a register
     */
    private static final Range REGISTER_INPUT_RANGE = new Range(1, Integer.MAX_VALUE);

    /**
     * valid input range for encoder module
     */
    private static final Range ENCODER_INPUT_RANGE = new Range(2, Integer.MAX_VALUE);

    /**
     * valid input range for decoder module
     */
    private static final Range DECODER_INPUT_RANGE = new Range(1, Integer.MAX_VALUE);

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
     * Or gate function
     * @param input input signals
     * @return evaluation of signals. True if ANY inputs are true, false otherwise
     * @throws IllegalArgumentException if input is null
     * @throws RangeValidationException if input width is less than 2
     */
    public static boolean or(boolean[] input) {
        validateNullAndWidth(input, OR_INPUT_RANGE);
        return IntStream.range(0, input.length)
                .mapToObj(i -> input[i])
                .anyMatch(signal -> signal);
    }

    /**
     * Xor gate function
     * @param input input signals
     * @return evaluation of signals. True if inputs are different, false otherwise
     * @throws IllegalArgumentException if input is null
     * @throws RangeValidationException if input width is NOT EXACTLY 2
     */
    public static boolean xor(boolean[] input) {
        validateNullAndWidth(input, XOR_INPUT_RANGE);
        return input[0] ^ input[1];
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
     * @throws IllegalArgumentException if toEncode is null
     * @throws RangeValidationException if toEncode's length is less than 2
     * @throws EncoderInputWidthException if toEncode's width is NOT a power of 2
     */
    public static boolean[] encode(boolean[] toEncode) {
        validateNullAndWidth(toEncode, ENCODER_INPUT_RANGE);
        //input width must be power of two
        if (toEncode.length < 2 || !isPowerOfTwo(toEncode.length)) {
            throw new EncoderInputWidthException(toEncode.length);
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

    /**
     * Decode an array of booleans into an array of booleans. For instance, a truth table for a 4-bit array is as follows
     *
     * 00 -- 0001
     * 01 -- 0010
     * 10 -- 0100
     * 11 -- 1000
     *
     * @param toDecode array to decode
     * @return encoded binary number
     * @throws IllegalArgumentException if toDecode is null
     * @throws RangeValidationException if toDecode's length less than 1
     */
    public static boolean[] decode(boolean[] toDecode) {
        validateNullAndWidth(toDecode, DECODER_INPUT_RANGE);
        //create decoded output initialized to false
        int outputWidth = 1 << toDecode.length;
        boolean[] decoded = new boolean[outputWidth];
        Arrays.fill(decoded, false);

        //what is the number represented by the encoded input?
        int sum = 0;
        for (int i=0; i<toDecode.length; i++) {
            int toAdd = toDecode[i] ? (1 << i) : 0;
            sum += toAdd;
        }
        //turn that bit on and return the value
        decoded[sum] = true;
        return decoded;
    }

    private static boolean[] validateNullAndWidth(boolean[] input, Range range) {
        return Validations.validateWidthInRange(Validations.validateNotNull(input), range);
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && Integer.bitCount(number) == 1;
    }

}
