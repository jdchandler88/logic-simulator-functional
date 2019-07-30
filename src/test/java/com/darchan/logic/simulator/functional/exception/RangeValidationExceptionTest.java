package com.darchan.logic.simulator.functional.exception;

import com.darchan.logic.simulator.functional.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeValidationExceptionTest {

    @Test
    void getRange() {
        Range expected = new Range(0, 1);
        RangeValidationException cut = new RangeValidationException(expected);
        assertEquals(expected, cut.getRange());
    }

}