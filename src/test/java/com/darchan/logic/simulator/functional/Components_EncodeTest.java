package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.EncoderInputWidthException;
import com.darchan.logic.simulator.functional.exception.InvalidEncoderInputValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_EncodeTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void encodeShouldHaveExpectedOutput(boolean[] input, boolean[] expected) {
        assertArrayEquals(expected, Components.encode(input));
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

    @Test
    void shouldThrowExceptionIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Components.encode(null));
    }

    @Test
    void shouldThrowExceptionIfInputIsNotAPowerOfTwo() {
        assertThrows(EncoderInputWidthException.class, () -> Components.encode(new boolean[3]));
    }

    @Test
    void shouldThrowExceptionIfNoInputsAreOn() {
        assertThrows(InvalidEncoderInputValueException.class, () -> Components.encode(new boolean[]{false, false}));
    }

    @Test
    void shouldThrowExceptionIfMoreThanOneInputIsOn() {
        assertThrows(InvalidEncoderInputValueException.class, () -> Components.encode(new boolean[]{true, true}));
    }

}
