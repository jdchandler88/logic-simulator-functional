package com.darchan.logic.simulator.functional;

import java.util.Arrays;

public final class Components {

    private Components() {}

    public static Boolean and(Boolean[] input) {
        return Arrays.stream(input)
                .allMatch(signal -> signal);
    }

}
