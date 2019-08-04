package com.darchan.logic.simulator.functional.intel8080;

public class Intel8080 {

    public final boolean[] addressBus;

    public final boolean[] dataBus;

    public final boolean sync;

    public final boolean dbIn;

    public final boolean ready;

    public final boolean wait;

    public final boolean wr;

    public final boolean hold;

    public final boolean hlda;

    public final boolean inte;

    public final boolean intt;

    public final boolean reset;

    public final boolean clk;

    Intel8080(
            boolean[] addressBus,
            boolean[] dataBus,
            boolean sync,
            boolean dbIn,
            boolean ready,
            boolean wait,
            boolean wr,
            boolean hold,
            boolean hlda,
            boolean inte,
            boolean intt,
            boolean reset,
            boolean clk
              ) {
        this.addressBus = addressBus;
        this.dataBus = dataBus;
        this.sync = sync;
        this.dbIn = dbIn;
        this.ready = ready;
        this.wait = wait;
        this.wr = wr;
        this.hold = hold;
        this.hlda = hlda;
        this.inte = inte;
        this.intt = intt;
        this.reset = reset;
        this.clk = clk;
    }


    static Intel8080 compute() {
        //do computation and return new state based on input state
        return null;
    }


}
