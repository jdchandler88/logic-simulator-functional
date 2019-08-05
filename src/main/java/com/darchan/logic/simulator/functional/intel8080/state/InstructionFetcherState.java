package com.darchan.logic.simulator.functional.intel8080.state;

/**
 * Internal state for the instruction fetcher. This state is for controlling how many bytes to fetch from memory
 * during instruction fetch.
 *
 * NOTE: One and ONLY ONE of the internal booleans should ever be true at one point in time
 */
public class InstructionFetcherState {

    /**
     * current state of the clock
     */
    public final boolean clock;

    /**
     * if true, then the instruction fetcher is in idle state
     */
    public final boolean idle;

    /**
     * if true, then the instruction fetcher is fetching the first instruction byte
     */
    public final boolean fetchByte1;

    /**
     * if true, then the instruction fetcher is fetching the second instruction byte
     */
    public final boolean fetchByte2;

    /**
     * if true, then the instruction fether is fetching the third instruction byte
     */
    public final boolean fetchByte3;

    /**
     * Creates state with the supplied params
     * @param clock clock value
     * @param idle idle state
     * @param fetchByte1 fetching byte 1
     * @param fetchByte2 fetching byte 2
     * @param fetchByte3 fetching byte 3
     */
    InstructionFetcherState(boolean clock, boolean idle, boolean fetchByte1, boolean fetchByte2, boolean fetchByte3) {
        this.clock = clock;
        this.idle = idle;
        this.fetchByte1 = fetchByte1;
        this.fetchByte2 = fetchByte2;
        this.fetchByte3 = fetchByte3;
    }

}
