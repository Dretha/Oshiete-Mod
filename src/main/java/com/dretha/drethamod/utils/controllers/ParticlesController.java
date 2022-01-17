package com.dretha.drethamod.utils.controllers;

public class ParticlesController{

    protected boolean isSpawn = false;
    public final int duration;
    protected int ticksPre = 0;

    public ParticlesController(int duration) {
        this.duration = duration;
    }

    public void activate(int ticksPre) {
        isSpawn = true;
        this.ticksPre = ticksPre;
    }

    public boolean active() {
        return isSpawn;
    }

    public void update(int ticks) {
        if (ticksPre+duration<ticks) {
            isSpawn = false;
        }
    }
}
