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
    void registerLatchesWhenItShould(boolean prevClk, boolean newClk, boolean[] prevValue, boolean[] newValue, boolean[] expected) {
        assertArrayEquals(expected, Components.register(prevClk, newClk, prevValue, newValue));
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(false, false, new boolean[]{false, false}, new boolean[]{true, true}, new boolean[]{false, false}), //clock was and still is low
                Arguments.of(false, true, new boolean[]{false, false}, new boolean[]{true, true}, new boolean[]{true, true}), //clock was low and has risen (REGISTER SHOULD LATCH NEW VALUE!)
                Arguments.of(true, false, new boolean[]{false, false}, new boolean[]{true, true}, new boolean[]{false, false}), //clock was high and has fallen
                Arguments.of(true, true, new boolean[]{false, false}, new boolean[]{true, true}, new boolean[]{false, false})      //clock was and still is high
        );
    }

    @ParameterizedTest
    @MethodSource("getNulls")
    void shouldThrowIllegalArgumentExceptionWhenAnyInputIsNull(boolean[] prevValue, boolean[] newValue) {
        assertThrows(IllegalArgumentException.class, () -> Components.register(true, true, prevValue, newValue));
    }

    static Stream<Arguments> getNulls() {
        return Stream.of(
                Arguments.of(null, new boolean[2]),
                Arguments.of(new boolean[2], null),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("getBadLengthArgs")
    void shouldThrowRangeValidationExceptionIfAnyInputIsZeroLength(boolean[] prevValue, boolean[] newValue) {
        assertThrows(RangeValidationException.class, () -> Components.register(true, true, prevValue, newValue));
    }

    static Stream<Arguments> getBadLengthArgs() {
        return Stream.of(
                Arguments.of(new boolean[0], new boolean[2]),
                Arguments.of(new boolean[2], new boolean[0])
        );
    }

    @Test
    void shouldThrowRegisterWidthMismatchExceptionIfWidthsAreDifferentLength() {
        assertThrows(RegisterInputWidthMismatchException.class, () -> Components.register(true, true, new boolean[2], new boolean[3]));
    }



}
