package com.darchan.logic.simulator.functional.intel8080.state;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFetcherStateBuilderTest {

    @Test
    void defaultConstructorBuildsCorrectInitialState() {
        InstructionFetcherState init = new InstructionFetcherStateBuilder().createInstructionDecoderState();
        assertAll(
                () -> assertTrue(init.idle),
                () -> assertFalse(init.fetchByte1),
                () -> assertFalse(init.fetchByte2),
                () -> assertFalse(init.fetchByte3)
        );
    }

    @ParameterizedTest
    @CsvSource(value={"true, false, false, false", "false, true, false, false", "false, false, true, false", "false, false, false, true"})
    void builderSetsCorrectValues(boolean idle, boolean fetchByte1, boolean fetchByte2, boolean fetchByte3) {
        InstructionFetcherState state = new InstructionFetcherStateBuilder()
                .setIdle(idle)
                .setFetchByte1(fetchByte1)
                .setFetchByte2(fetchByte2)
                .setFetchByte3(fetchByte3)
                .createInstructionDecoderState();
        assertAll(
                () -> assertEquals(idle, state.idle),
                () -> assertEquals(fetchByte1, fetchByte1),
                () -> assertEquals(fetchByte2, fetchByte2),
                () -> assertEquals(fetchByte3, fetchByte3)
        );
    }

}