package com.darchan.logic.simulator.functional;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class Components {

    /**
     * valid input range for and gate
     */
    static final Range AND_INPUT_RANGE = new Range(2, Integer.MAX_VALUE);

    private Components() {}

    public static boolean and(boolean[] input) {
        Validations.validateWidthInRange(Validations.validateNotNull(input), AND_INPUT_RANGE);
        return IntStream.range(0, input.length)
                .mapToObj(i -> input[i])
                .allMatch(signal -> signal);
    }

}
