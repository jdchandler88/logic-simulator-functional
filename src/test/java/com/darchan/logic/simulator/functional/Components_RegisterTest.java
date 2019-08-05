package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import com.darchan.logic.simulator.functional.exception.RegisterInputWidthMismatchException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Components_RegisterTest {

    @ParameterizedTest
    @MethodSource("getArguments")
    void registerLatchesWhenItShould(boolean prevClk, boolean newClk, Boolean[] prevValue, Boolean[] newValue, Boolean[] expected) {
        assertArrayEquals(expected, Components.register(prevClk, newClk, prevValue, newValue));
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(false, false, new Boolean[]{false, false}, new Boolean[]{true, true}, new Boolean[]{false, false}), //clock was and still is low
                Arguments.of(false, true, new Boolean[]{false, false}, new Boolean[]{true, true}, new Boolean[]{true, true}), //clock was low and has risen (REGISTER SHOULD LATCH NEW VALUE!)
                Arguments.of(true, false, new Boolean[]{false, false}, new Boolean[]{true, true}, new Boolean[]{false, false}), //clock was high and has fallen
                Arguments.of(true, true, new Boolean[]{false, false}, new Boolean[]{true, true}, new Boolean[]{false, false})      //clock was and still is high
        );
    }

    @ParameterizedTest
    @MethodSource("getNulls")
    void shouldThrowIllegalArgumentExceptionWhenAnyInputIsNull(Boolean[] prevValue, Boolean[] newValue) {
        assertThrows(IllegalArgumentException.class, () -> Components.register(true, true, prevValue, newValue));
    }

    static Stream<Arguments> getNulls() {
        return Stream.of(
                Arguments.of(null, new Boolean[2]),
                Arguments.of(new Boolean[2], null),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("getBadLengthArgs")
    void shouldThrowRangeValidationExceptionIfAnyInputIsZeroLength(Boolean[] prevValue, Boolean[] newValue) {
        assertThrows(RangeValidationException.class, () -> Components.register(true, true, prevValue, newValue));
    }

    static Stream<Arguments> getBadLengthArgs() {
        return Stream.of(
                Arguments.of(new Boolean[0], new Boolean[2]),
                Arguments.of(new Boolean[2], new Boolean[0])
        );
    }

    @Test
    void shouldThrowRegisterWidthMismatchExceptionIfWidthsAreDifferentLength() {
        assertThrows(RegisterInputWidthMismatchException.class, () -> Components.register(true, true, new Boolean[2], new Boolean[3]));
    }



}
