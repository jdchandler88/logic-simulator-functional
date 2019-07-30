package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RegisterInputWidthMismatchException;
import com.darchan.logic.simulator.functional.exception.RangeValidationException;

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

    private static boolean[] validateNullAndWidth(boolean[] input, Range range) {
        return Validations.validateWidthInRange(Validations.validateNotNull(input), range);
    }

}
