package com.darchan.logic.simulator.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Validations_ValidateNotNullTest {

    @Test
    void shouldReturnInputWhenInputIsNotNull() {
        Object expected = Boolean.TRUE;
        assertEquals(expected, Validations.validateNotNull(expected));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Validations.validateNotNull(null));
    }

}
