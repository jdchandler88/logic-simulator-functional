package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_FullAdderTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void fullAdderShouldReturnExpected(boolean[] inputs, boolean[] expected) {
        assertArrayEquals(expected, Components.fullAdder(inputs));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
            Arguments.of(new boolean[]{false, false, false}, new boolean[]{false, false}),
            Arguments.of(new boolean[]{true, false, false}, new boolean[]{true, false}),
            Arguments.of(new boolean[]{false, true, false}, new boolean[]{true, false}),
            Arguments.of(new boolean[]{true, true, false}, new boolean[]{false, true}),
            Arguments.of(new boolean[]{false, false, true}, new boolean[]{true, false}),
            Arguments.of(new boolean[]{true, false, true}, new boolean[]{false, true}),
            Arguments.of(new boolean[]{false, true, true}, new boolean[]{false, true}),
            Arguments.of(new boolean[]{true, true, true}, new boolean[]{true, true})
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void fullAdderShouldThrowException(Class<? extends Throwable> clazz, boolean[] inputs) {
        assertThrows(clazz, () -> Components.fullAdder(inputs));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null),
                Arguments.of(RangeValidationException.class, new boolean[2]),
                Arguments.of(RangeValidationException.class, new boolean[4])
        );
    }

}
