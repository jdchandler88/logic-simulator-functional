package com.darchan.logic.simulator.functional.intel8080.state;

public class State {

    public final boolean clock;

    public final boolean[] instruction;

    public final CMCState cmcState;

    public State(boolean clock, boolean[] instruction, CMCState cmcState) {
        this.clock = clock;
        this.instruction = instruction;
        this.cmcState = cmcState;
    }

}
