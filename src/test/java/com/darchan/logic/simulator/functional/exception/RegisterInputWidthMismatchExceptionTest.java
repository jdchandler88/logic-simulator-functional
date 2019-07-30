package com.darchan.logic.simulator.functional.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterInputWidthMismatchExceptionTest {

    @Test
    void messageShouldBeExpected() {
        int width1 = 2;
        int width2 = 3;
        RegisterInputWidthMismatchException cut = new RegisterInputWidthMismatchException(width1, width2);
        assertEquals(String.format(RegisterInputWidthMismatchException.REGISTER_INPUT_WIDTH_MISMATCH_ERROR_MESSAGE, width1, width2), cut.getMessage());
    }

}