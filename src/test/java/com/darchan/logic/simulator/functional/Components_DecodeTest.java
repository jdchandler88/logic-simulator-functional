package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_DecodeTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void encodeReturnsExpectedValue(boolean[] expected, boolean[] input) {
        assertArrayEquals(expected, Components.decode(input));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new boolean[]{true, false}, new boolean[]{false}),
                Arguments.of(new boolean[]{false, true}, new boolean[]{true}),

                Arguments.of(new boolean[]{true, false, false, false}, new boolean[]{false, false}),
                Arguments.of(new boolean[]{false, true, false, false}, new boolean[]{true, false}),
                Arguments.of(new boolean[]{false, false, true, false}, new boolean[]{false, true}),
                Arguments.of(new boolean[]{false, false, false, true}, new boolean[]{true, true}),

                Arguments.of(new boolean[]{true, false, false, false, false, false, false, false}, new boolean[]{false, false, false}),
                Arguments.of(new boolean[]{false, true, false, false, false, false, false, false}, new boolean[]{true, false, false}),
                Arguments.of(new boolean[]{false, false, true, false, false, false, false, false}, new boolean[]{false, true, false}),
                Arguments.of(new boolean[]{false, false, false, true, false, false, false, false}, new boolean[]{true, true, false}),
                Arguments.of(new boolean[]{false, false, false, false, true, false, false, false}, new boolean[]{false, false, true}),
                Arguments.of(new boolean[]{false, false, false, false, false, true, false, false}, new boolean[]{true, false, true}),
                Arguments.of(new boolean[]{false, false, false, false, false, false, true, false}, new boolean[]{false, true, true}),
                Arguments.of(new boolean[]{false, false, false, false, false, false, false, true}, new boolean[]{true, true, true})
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void encodeShouldThrowAppropriateException(Class clazz, boolean[] input) {
        assertThrows(clazz, () -> Components.decode(input));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
            Arguments.of(IllegalArgumentException.class, null),
            Arguments.of(RangeValidationException.class, new boolean[0])
        );
    }

}
