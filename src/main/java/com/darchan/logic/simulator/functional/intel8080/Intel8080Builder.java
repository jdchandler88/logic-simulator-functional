package com.darchan.logic.simulator.functional.intel8080;

public class Intel8080Builder {

    private boolean[] addressBus;
    private boolean[] dataBus;
    private boolean sync;
    private boolean dbIn;
    private boolean ready;
    private boolean wait;
    private boolean wr;
    private boolean hold;
    private boolean hlda;
    private boolean inte;
    private boolean intt;
    private boolean reset;
    private boolean clk;

    public Intel8080Builder() {
    }

    public Intel8080Builder(Intel8080 toCopy) {
        this.addressBus = toCopy.addressBus;
        this.dataBus = toCopy.dataBus;
        this.sync = toCopy.sync;
        this.dbIn = toCopy.dbIn;
        this.ready = toCopy.ready;
        this.wait = toCopy.wait;
        this.wr = toCopy.wr;
        this.hold = toCopy.hold;
        this.hlda = toCopy.hlda;
        this.inte = toCopy.inte;
        this.intt = toCopy.intt;
        this.reset = toCopy.reset;
        this.clk = toCopy.clk;
    }

    public Intel8080Builder setAddressBus(boolean[] addressBus) {
        this.addressBus = addressBus;
        return this;
    }

    public Intel8080Builder setDataBus(boolean[] dataBus) {
        this.dataBus = dataBus;
        return this;
    }

    public Intel8080Builder setSync(boolean sync) {
        this.sync = sync;
        return this;
    }

    public Intel8080Builder setDbIn(boolean dbIn) {
        this.dbIn = dbIn;
        return this;
    }

    public Intel8080Builder setReady(boolean ready) {
        this.ready = ready;
        return this;
    }

    public Intel8080Builder setWait(boolean wait) {
        this.wait = wait;
        return this;
    }

    public Intel8080Builder setWr(boolean wr) {
        this.wr = wr;
        return this;
    }

    public Intel8080Builder setHold(boolean hold) {
        this.hold = hold;
        return this;
    }

    public Intel8080Builder setHlda(boolean hlda) {
        this.hlda = hlda;
        return this;
    }

    public Intel8080Builder setInte(boolean inte) {
        this.inte = inte;
        return this;
    }

    public Intel8080Builder setIntt(boolean intt) {
        this.intt = intt;
        return this;
    }

    public Intel8080Builder setReset(boolean reset) {
        this.reset = reset;
        return this;
    }

    public Intel8080Builder setClk(boolean clk) {
        this.clk = clk;
        return this;
    }

    public Intel8080 createIntel8080() {
        return new Intel8080(addressBus, dataBus, sync, dbIn, ready, wait, wr, hold, hlda, inte, intt, reset, clk);
    }
}