package com.darchan.logic.simulator.functional;

import com.darchan.logic.simulator.functional.exception.InvalidRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    private Range cut;

    private final int min = 0;

    private final int max = 1;

    @BeforeEach
    void init() {
        cut = new Range(min, max);
    }

    @Test
    void getMin() {
        assertEquals(min, cut.getMin());
    }

    @Test
    void getMax() {
        assertEquals(max, cut.getMax());
    }

    @Test
    void toString1() {
        assertEquals("[0,1]", cut.toString());
    }

    @Test
    void shouldThrowInvalidRangeExceptionIfMinGreaterThanMax() {
        assertThrows(InvalidRangeException.class, () -> new Range(1, 0));
    }


}