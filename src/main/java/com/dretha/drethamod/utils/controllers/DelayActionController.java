package com.dretha.drethamod.utils.controllers;

public class DelayActionController extends ActionController{

    protected final int delay;

    public DelayActionController(int delay, int interval) {
        super(interval);
        this.delay = delay;
        ticksPre = -delay-interval;
    }

    @Override
    public boolean endAct(int ticks) {
        return ticksPre+delay+interval<ticks;
    }

    public boolean isAct(int ticks) {
        boolean b = ticksPre+delay==ticks && ticksPre>0;
        if (b)
            ticksPre--;
        return b;
    }

    public boolean isEndAct(int ticks) {
        boolean b = ticksPre+delay+interval==ticks && ticksPre>0;
        if (b)
            ticksPre--;
        return b;
    }
}
