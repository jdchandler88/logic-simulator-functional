package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_DecoderTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void encodeReturnsExpectedValue(Boolean[] expected, Boolean[] input) {
        assertArrayEquals(expected, Components.decoder(input));
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

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void encodeShouldThrowAppropriateException(Class clazz, Boolean[] input) {
        assertThrows(clazz, () -> Components.decoder(input));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
            Arguments.of(IllegalArgumentException.class, null),
            Arguments.of(RangeValidationException.class, new Boolean[0])
        );
    }

}
