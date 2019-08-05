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
    void xorShouldReturnExpected(Boolean[] inputs, boolean expected) {
        assertEquals(expected, Components.xor(inputs[0], inputs[1]));
    }

    static Stream<Arguments> getInputs() {
        return Stream.of(
                Arguments.of(new Boolean[]{false, false}, false),
                Arguments.of(new Boolean[]{true, false}, true),
                Arguments.of(new Boolean[]{false, true}, true),
                Arguments.of(new Boolean[]{true, true}, false)
        );
    }

}
