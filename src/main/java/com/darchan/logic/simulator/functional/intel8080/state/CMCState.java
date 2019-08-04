package com.darchan.logic.simulator.functional.intel8080.state;

public class CMCState {

    private final boolean instructionMatch;

    private final boolean readCarryFromRegisterAndInvert;

    private final boolean writeComplementedCarryToCarryFlag;

    public CMCState(boolean instructionMatch, boolean readCarryFromRegisterAndInvert, boolean writeComplementedCarryToCarryFlag) {
        this.instructionMatch = instructionMatch;
        this.readCarryFromRegisterAndInvert = readCarryFromRegisterAndInvert;
        this.writeComplementedCarryToCarryFlag = writeComplementedCarryToCarryFlag;
    }



}
