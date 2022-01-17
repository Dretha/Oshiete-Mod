package com.dretha.drethamod.utils.controllers;

public class SmellController extends ActionController {

    public final int radius;
    public final int duration;

    public SmellController(int radius, int duration) {
        super(duration, true);
        this.ticksPre = -duration;
        this.radius = radius;
        this.duration = duration;
    }
}
