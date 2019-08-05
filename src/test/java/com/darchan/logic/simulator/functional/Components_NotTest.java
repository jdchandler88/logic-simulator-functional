package com.darchan.logic.simulator.functional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Components_NotTest {

    @ParameterizedTest
    @CsvSource(value = {"true", "false"})
    void notShouldReturnInverseOfInput(boolean input) {
        assertEquals(!input, Components.not(input));
    }

}
