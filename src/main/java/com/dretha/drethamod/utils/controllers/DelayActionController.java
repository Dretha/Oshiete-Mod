package com.dretha.drethamod.utils.controllers;

public class DelayActionController extends ActionController{

    protected final int delay;

    public DelayActionController(int delay, int interval) {
        super(interval);
        this.delay = delay;
    }

    @Override
    public boolean endAct(int ticks) {
        return ticksPre+delay+interval<ticks;
    }

    public boolean isAct(int ticks) {
        return ticksPre+delay+interval==ticks && !endAct(ticks);
    }

    public boolean isDelay(int ticks) {
        return ticksPre<=ticks && !isAct(ticks) && !endAct(ticks);
    }
}
