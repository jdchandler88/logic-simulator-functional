package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_XorTest {

    @ParameterizedTest
    @MethodSource("getInputs")
    void xorShouldReturnExpected(boolean[] inputs, boolean expected) {
        assertEquals(expected, Components.xor(inputs));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new boolean[]{false, false}, false),
                Arguments.of(new boolean[]{true, false}, true),
                Arguments.of(new boolean[]{false, true}, true),
                Arguments.of(new boolean[]{true, true}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void xorShouldThrowException(Class<? extends Throwable> clazz, boolean[] inputs) {
        assertThrows(clazz, () -> Components.xor(inputs));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
            Arguments.of(IllegalArgumentException.class, null),
            Arguments.of(RangeValidationException.class, new boolean[1]),
            Arguments.of(RangeValidationException.class, new boolean[3])
        );
    }
}
