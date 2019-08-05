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
    void fullAdderShouldReturnExpected(Boolean[] inputs, Boolean[] expected) {
        assertArrayEquals(expected, Components.fullAdder(inputs));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
            Arguments.of(new Boolean[]{false, false, false}, new Boolean[]{false, false}),
            Arguments.of(new Boolean[]{true, false, false}, new Boolean[]{true, false}),
            Arguments.of(new Boolean[]{false, true, false}, new Boolean[]{true, false}),
            Arguments.of(new Boolean[]{true, true, false}, new Boolean[]{false, true}),
            Arguments.of(new Boolean[]{false, false, true}, new Boolean[]{true, false}),
            Arguments.of(new Boolean[]{true, false, true}, new Boolean[]{false, true}),
            Arguments.of(new Boolean[]{false, true, true}, new Boolean[]{false, true}),
            Arguments.of(new Boolean[]{true, true, true}, new Boolean[]{true, true})
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void fullAdderShouldThrowException(Class<? extends Throwable> clazz, Boolean[] inputs) {
        assertThrows(clazz, () -> Components.fullAdder(inputs));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null),
                Arguments.of(RangeValidationException.class, new Boolean[2]),
                Arguments.of(RangeValidationException.class, new Boolean[4])
        );
    }

}
