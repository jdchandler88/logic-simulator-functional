package com.darchan.logic.simulator.functional.intel8080;

public class Control {



    public static boolean[] control() {

        //cmc; enable logic that will read the carry bit, invert it, and latch it back in



        return null;

    }

    private static class State {

        private boolean clock;

        private boolean[] instruction;

        private CMCState cmcState;

    }

    private static class CMCState {

        private final boolean instructionMatch;

        private final boolean readCarryFromRegisterAndInvert;

        private final boolean writeComplementedCarryToCarryFlag;

        CMCState(boolean instructionMatch, boolean readCarryFromRegisterAndInvert, boolean writeComplementedCarryToCarryFlag) {
            this.instructionMatch = instructionMatch;
            this.readCarryFromRegisterAndInvert = readCarryFromRegisterAndInvert;
            this.writeComplementedCarryToCarryFlag = writeComplementedCarryToCarryFlag;
        }

    }


}
