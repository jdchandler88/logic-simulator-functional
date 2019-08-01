package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_OrTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void orShouldReturnExpectedValue(boolean[] inputs, boolean expected) {
        assertEquals(expected, Components.or(inputs));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new boolean[]{false, false}, false),
                Arguments.of(new boolean[]{true, false}, true),
                Arguments.of(new boolean[]{false, true}, true),
                Arguments.of(new boolean[]{true, true}, true),


                Arguments.of(new boolean[]{false, false, false}, false),
                Arguments.of(new boolean[]{true, false, false}, true),
                Arguments.of(new boolean[]{false, true, false}, true),
                Arguments.of(new boolean[]{true, true, false}, true),
                Arguments.of(new boolean[]{false, false, true}, true),
                Arguments.of(new boolean[]{true, false, true}, true),
                Arguments.of(new boolean[]{false, true, true}, true),
                Arguments.of(new boolean[]{true, true, true}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void orShouldThrowException(Class<? extends Throwable> clazz, boolean[] input) {
        assertThrows(clazz, () -> Components.or(input));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
            Arguments.of(IllegalArgumentException.class, null),
            Arguments.of(RangeValidationException.class, new boolean[1])
        );
    }

}
