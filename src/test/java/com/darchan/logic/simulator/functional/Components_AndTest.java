package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_AndTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void shouldReturnExpectedValue(Boolean[] input, boolean expected) {
        assertEquals(expected, Components.and(input));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new Boolean[]{false, false}, false),
                Arguments.of(new Boolean[]{false, true}, false),
                Arguments.of(new Boolean[]{true, false}, false),
                Arguments.of(new Boolean[]{true, true}, true),


                Arguments.of(new Boolean[]{false, false, false}, false),
                Arguments.of(new Boolean[]{false, false, true}, false),
                Arguments.of(new Boolean[]{false, true, false}, false),
                Arguments.of(new Boolean[]{false, true, true}, false),
                Arguments.of(new Boolean[]{true, false, false}, false),
                Arguments.of(new Boolean[]{true, false, true}, false),
                Arguments.of(new Boolean[]{true, true, false}, false),
                Arguments.of(new Boolean[]{true, true, true}, true)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Components.and(null));
    }

    @Test
    void shouldThrowInvalidRangeExceptionIfInputWidthIsLessThan2() {
        assertThrows(RangeValidationException.class, () -> Components.and(new Boolean[1]));
    }

}
