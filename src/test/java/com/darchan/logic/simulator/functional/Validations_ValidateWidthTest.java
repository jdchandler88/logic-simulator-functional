package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Validations_ValidateWidthTest {

    private final Range testRange = new Range(2, 4);

    @Test
    void shouldReturnFalseWhenInputIsGreaterThanRange() {
        assertThrows(RangeValidationException.class, () -> Validations.validateWidthInRange(new boolean[5], testRange));
    }

    @Test
    void shouldReturnFalseWhenInputIsLessThanRange() {
        assertThrows(RangeValidationException.class, () -> Validations.validateWidthInRange(new boolean[1], testRange));
    }

    @Test
    void shouldReturnTrueWhenInputIsWithinRange() {
        boolean[] input = new boolean[3];
        assertEquals(input, Validations.validateWidthInRange(input, testRange));
    }

    @Test
    void shouldThrowExceptionIfRangeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validations.validateWidthInRange(new boolean[3], null));
    }

    @Test
    void shouldThrowExceptionIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validations.validateWidthInRange(null, testRange));
    }




}
