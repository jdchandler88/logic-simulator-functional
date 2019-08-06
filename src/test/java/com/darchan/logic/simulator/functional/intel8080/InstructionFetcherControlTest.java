package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.intel8080.state.InstructionFetcherState;
import com.darchan.logic.simulator.functional.intel8080.state.InstructionFetcherStateBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFetcherControlTest {

    private static final Boolean[] ONE_BYTE_INSTRUCTION = new Boolean[]{true, true, true, true, true, true, true, true};

    private static final Boolean[] TWO_BYTE_INSTRUCTION = InstructionFetcherControl.TWO_BYTE_INSTRUCTIONS[0];

    private static final Boolean[] THREE_BYTE_INSTRUCTION = InstructionFetcherControl.THREE_BYTE_INSTRUCTIONS[0];

    private static final InstructionFetcherState INITIAL_IDLE = createFetcherState(false,true, false, false, false);

    private static final InstructionFetcherState POST_IDLE = createFetcherState(true,true, false, false, false);

    private static final InstructionFetcherState INITIAL_FETCH_BYTE_1 = createFetcherState(false,false, true, false, false);

    private static final InstructionFetcherState POST_FETCH_BYTE_1 = createFetcherState(true,false, true, false, false);

    private static final InstructionFetcherState INITIAL_FETCH_BYTE_2 = createFetcherState(false,false, false, true, false);

    private static final InstructionFetcherState POST_FETCH_BYTE_2 = createFetcherState(true,false, false, true, false);

    private static final InstructionFetcherState INITIAL_FETCH_BYTE_3 = createFetcherState(false,false, false, false, true);

    private static final InstructionFetcherState POST_FETCH_BYTE_3 = createFetcherState(true,false, false, false, true);

    @ParameterizedTest
    @MethodSource("getStates")
    void shouldTransitionProperly(InstructionFetcherState prev, Boolean fetch, Boolean[] instructionRegister, InstructionFetcherState expected) {
        assertEquals(expected, InstructionFetcherControl.tick(true, fetch, instructionRegister, prev));
    }

    static Stream<Arguments> getStates() {
        return Stream.of(
            //IDLE
            Arguments.of(INITIAL_IDLE, false, ONE_BYTE_INSTRUCTION, POST_IDLE),  //should remain in idle state when fetch is false

            //ONE BYTE INSTRUCTION
            Arguments.of(INITIAL_IDLE, true, ONE_BYTE_INSTRUCTION, POST_FETCH_BYTE_1), //should go to fetch-byte-1 when fetch goes high
            Arguments.of(INITIAL_FETCH_BYTE_1, true, ONE_BYTE_INSTRUCTION, POST_IDLE), //should go to idle after 1-byte instruction finished

            //TWO BYTE INSTRUCTION
            Arguments.of(INITIAL_IDLE, true, TWO_BYTE_INSTRUCTION, POST_FETCH_BYTE_1),   //should go to fetch-byte-1 when fetch goes high
            Arguments.of(INITIAL_FETCH_BYTE_1, true, TWO_BYTE_INSTRUCTION, POST_FETCH_BYTE_2),   //should go to fetch-byte-2 after fetch-byte-1
            Arguments.of(INITIAL_FETCH_BYTE_2, true, TWO_BYTE_INSTRUCTION, POST_IDLE),   //should go back to idle after fetching 2 bytes

            //THREE BYTE INSTRUCTION
            Arguments.of(INITIAL_IDLE, true, THREE_BYTE_INSTRUCTION, POST_FETCH_BYTE_1), //should go to fetch-byte-1 when fetch goes high
            Arguments.of(INITIAL_FETCH_BYTE_1, true, THREE_BYTE_INSTRUCTION, POST_FETCH_BYTE_2), //should go to fetch-byte-2 after fetch-byte-1
            Arguments.of(INITIAL_FETCH_BYTE_2, true, THREE_BYTE_INSTRUCTION, POST_FETCH_BYTE_3), //should go to fetch-byte-3 after fetch-byte-2
            Arguments.of(INITIAL_FETCH_BYTE_3, true, THREE_BYTE_INSTRUCTION, POST_IDLE) //should go back to idle after fetching 3 bytes
        );
    }


    static InstructionFetcherState createFetcherState(boolean clock, boolean idle, boolean f1, boolean f2, boolean f3) {
        return new InstructionFetcherStateBuilder()
                .setClock(clock)    //always use false in this test since transitions only occur on positive edge of clk
                .setIdle(idle)
                .setFetchByte1(f1)
                .setFetchByte2(f2)
                .setFetchByte3(f3)
                .createInstructionDecoderState();
    }

}