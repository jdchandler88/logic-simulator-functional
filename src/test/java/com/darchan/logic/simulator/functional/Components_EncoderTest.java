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

class Components_EncoderTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void encodeShouldHaveExpectedOutput(Boolean[] input, Boolean[] expected) {
        assertArrayEquals(expected, Components.encoder(input));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new Boolean[]{true, false}, new Boolean[]{false}),
                Arguments.of(new Boolean[]{false, true}, new Boolean[]{true}),

                Arguments.of(new Boolean[]{true, false, false, false}, new Boolean[]{false, false}),
                Arguments.of(new Boolean[]{false, true, false, false}, new Boolean[]{true, false}),
                Arguments.of(new Boolean[]{false, false, true, false}, new Boolean[]{false, true}),
                Arguments.of(new Boolean[]{false, false, false, true}, new Boolean[]{true, true}),

                Arguments.of(new Boolean[]{true, false, false, false, false, false, false, false}, new Boolean[]{false, false, false}),
                Arguments.of(new Boolean[]{false, true, false, false, false, false, false, false}, new Boolean[]{true, false, false}),
                Arguments.of(new Boolean[]{false, false, true, false, false, false, false, false}, new Boolean[]{false, true, false}),
                Arguments.of(new Boolean[]{false, false, false, true, false, false, false, false}, new Boolean[]{true, true, false}),
                Arguments.of(new Boolean[]{false, false, false, false, true, false, false, false}, new Boolean[]{false, false, true}),
                Arguments.of(new Boolean[]{false, false, false, false, false, true, false, false}, new Boolean[]{true, false, true}),
                Arguments.of(new Boolean[]{false, false, false, false, false, false, true, false}, new Boolean[]{false, true, true}),
                Arguments.of(new Boolean[]{false, false, false, false, false, false, false, true}, new Boolean[]{true, true, true})
        );
    }

    @Test
    void shouldThrowExceptionIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Components.encoder(null));
    }

    @Test
    void shouldThrowExceptionIfInputIsNotAPowerOfTwo() {
        assertThrows(EncoderInputWidthException.class, () -> Components.encoder(new Boolean[3]));
    }

    @Test
    void shouldThrowExceptionIfNoInputsAreOn() {
        assertThrows(InvalidEncoderInputValueException.class, () -> Components.encoder(new Boolean[]{false, false}));
    }

    @Test
    void shouldThrowExceptionIfMoreThanOneInputIsOn() {
        assertThrows(InvalidEncoderInputValueException.class, () -> Components.encoder(new Boolean[]{true, true}));
    }

}
