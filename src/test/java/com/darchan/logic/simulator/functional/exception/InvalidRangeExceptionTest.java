package com.darchan.logic.simulator.functional.exception;

import com.darchan.logic.simulator.functional.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRangeExceptionTest {

    @Test
    void errorMessageShouldBeExpected() {
        int min = 100;
        int max = 0;
        InvalidRangeException cut = new InvalidRangeException(min, max);
        assertEquals(String.format(InvalidRangeException.ERROR_MESSAGE_TEMPLATE, min, max), cut.getMessage());
    }

}