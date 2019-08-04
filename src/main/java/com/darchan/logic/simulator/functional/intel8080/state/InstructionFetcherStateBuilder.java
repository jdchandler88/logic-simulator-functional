package com.darchan.logic.simulator.functional.intel8080.state;

/**
 * Builder for instruction fetcher state. The builder pattern is used since we are trying to build this simulator
 * using a more functional approach. Thus, state is immutable. To save on completely creating new objects, the
 * builder can be used for copying and then modulating the state that has changed.
 *
 * See {@link InstructionFetcherState} for information on what each member means.
 */
public class InstructionFetcherStateBuilder {

    private boolean idle;
    private boolean fetchByte1;
    private boolean fetchByte2;
    private boolean fetchByte3;

    /**
     * Creates initial {@link InstructionFetcherState}. The 'idle' state is set to true.
     */
    public InstructionFetcherStateBuilder() {
        this.idle = true;
        this.fetchByte1 = false;
        this.fetchByte2 = false;
        this.fetchByte3 = false;
    }

    /**
     * Copies supplied {@link InstructionFetcherState}
     * @param toCopy state to copy
     */
    public InstructionFetcherStateBuilder(InstructionFetcherState toCopy) {
        this.idle = toCopy.idle;
        this.fetchByte1 = toCopy.fetchByte1;
        this.fetchByte2 = toCopy.fetchByte2;
        this.fetchByte3 = toCopy.fetchByte3;
    }

    public InstructionFetcherStateBuilder setIdle(boolean idle) {
        this.idle = idle;
        return this;
    }

    public InstructionFetcherStateBuilder setFetchByte1(boolean fetchByte1) {
        this.fetchByte1 = fetchByte1;
        return this;
    }

    public InstructionFetcherStateBuilder setFetchByte2(boolean fetchByte2) {
        this.fetchByte2 = fetchByte2;
        return this;
    }

    public InstructionFetcherStateBuilder setFetchByte3(boolean fetchByte3) {
        this.fetchByte3 = fetchByte3;
        return this;
    }

    public InstructionFetcherState createInstructionDecoderState() {
        return new InstructionFetcherState(idle, fetchByte1, fetchByte2, fetchByte3);
    }

}