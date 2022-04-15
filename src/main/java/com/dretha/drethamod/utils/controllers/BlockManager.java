package com.dretha.drethamod.utils.controllers;

/**
 *
 */
public class BlockManager {

    protected int ticksPre = 0;
    protected int interval = 0;
    protected int delay = 0;

    public void startBlocking(int ticksPre, int interval, int delay) {
        if (endCicle(ticksPre)) {
            this.ticksPre = ticksPre;
            this.interval = interval;
            this.delay = delay;
        }
    }

    public boolean endBlock(int ticks) {
        return ticksPre + interval < ticks;
    }

    public boolean endCicle(int ticks) {
        return ticksPre + interval + delay < ticks;
    }
}
