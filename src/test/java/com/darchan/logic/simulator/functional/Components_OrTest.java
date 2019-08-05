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
    void orShouldReturnExpectedValue(Boolean[] inputs, boolean expected) {
        assertEquals(expected, Components.or(inputs));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new Boolean[]{false, false}, false),
                Arguments.of(new Boolean[]{true, false}, true),
                Arguments.of(new Boolean[]{false, true}, true),
                Arguments.of(new Boolean[]{true, true}, true),


                Arguments.of(new Boolean[]{false, false, false}, false),
                Arguments.of(new Boolean[]{true, false, false}, true),
                Arguments.of(new Boolean[]{false, true, false}, true),
                Arguments.of(new Boolean[]{true, true, false}, true),
                Arguments.of(new Boolean[]{false, false, true}, true),
                Arguments.of(new Boolean[]{true, false, true}, true),
                Arguments.of(new Boolean[]{false, true, true}, true),
                Arguments.of(new Boolean[]{true, true, true}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void orShouldThrowException(Class<? extends Throwable> clazz, Boolean[] input) {
        assertThrows(clazz, () -> Components.or(input));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
            Arguments.of(IllegalArgumentException.class, null),
            Arguments.of(RangeValidationException.class, new Boolean[1])
        );
    }

}
