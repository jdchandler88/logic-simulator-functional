package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.Components;
import com.darchan.logic.simulator.functional.intel8080.state.InstructionFetcherState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.darchan.logic.simulator.functional.Components.*;

public class InstructionFetcherControl {

    private static final Boolean[][] TWO_BYTE_INSTRUCTIONS = new Boolean[][]{
            {false, false, false, false, false, false, false, false}
    };

    private static Boolean[][] THREE_BYTE_INSTRUCTIONS = new Boolean[][]{
            {false, false, false, false, false, false, false, true}
    };

    private static final List<Function<Boolean[], Boolean>> IS_TWO_BYTES;

    private static final List<Function<Boolean[], Boolean>> IS_THREE_BYTES;

    static {
        //construct a list of functions. this is more analogous to having hardware per instruction to determine match
        IS_TWO_BYTES = new ArrayList<>();
        for (int i=0; i<TWO_BYTE_INSTRUCTIONS.length; i++) {
            Boolean[] twoByteInstruction = TWO_BYTE_INSTRUCTIONS[i];
            IS_TWO_BYTES.add(instr -> and(and(instr[0], twoByteInstruction[0]), and(instr[1], twoByteInstruction[1]), and(instr[2], twoByteInstruction[2]), and(instr[3], twoByteInstruction[3]), and(instr[4], twoByteInstruction[4]), and(instr[5], twoByteInstruction[5]), and(instr[6], twoByteInstruction[6]), and(instr[7], twoByteInstruction[7])));
        }

        IS_THREE_BYTES = new ArrayList<>();
        for (int i=0; i<THREE_BYTE_INSTRUCTIONS.length; i++) {
            Boolean[] threeByteInstruction = THREE_BYTE_INSTRUCTIONS[i];
            IS_THREE_BYTES.add(instr -> and(and(instr[0], threeByteInstruction[0]), and(instr[1], threeByteInstruction[1]), and(instr[2], threeByteInstruction[2]), and(instr[3], threeByteInstruction[3]), and(instr[4], threeByteInstruction[4]), and(instr[5], threeByteInstruction[5]), and(instr[6], threeByteInstruction[6]), and(instr[7], threeByteInstruction[7])));
        }
    }


    public InstructionFetcherState tick(boolean clock, boolean fetch, Boolean[] instructionRegister, InstructionFetcherState prevState) {

        //will need to un-hardcode these later. only working on 1-byte instructions for now
        boolean is2ByteInstruction = is2ByteInstruction(instructionRegister);
        boolean is3ByteInstruction = is3ByteInstruction(instructionRegister);


        boolean twoOrThreeByteInstruction = or(is2ByteInstruction, is3ByteInstruction);
        boolean idleTransitions = or(not(fetch), and(prevState.fetchByte1, twoOrThreeByteInstruction), and(prevState.fetchByte2, is3ByteInstruction), prevState.fetchByte3);
        boolean idle = Components.register(clock, prevState.clock, new Boolean[]{idleTransitions}, new Boolean[]{prevState.idle})[0];

        return null;
    }

    private static boolean is2ByteInstruction(Boolean[] firstInstructionByte) {
        Boolean[] matches = IS_TWO_BYTES.stream()
            .map(f -> f.apply(firstInstructionByte))
                .toArray(Boolean[]::new);
        return or(matches);
    }

    private static boolean is3ByteInstruction(Boolean[] firstInstructionByte) {
        Boolean[] matches = IS_THREE_BYTES.stream()
                .map(f -> f.apply(firstInstructionByte))
                .toArray(Boolean[]::new);
        return or(matches);
    }

}
