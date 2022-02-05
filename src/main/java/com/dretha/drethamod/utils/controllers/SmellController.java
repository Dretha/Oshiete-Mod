package com.dretha.drethamod.utils.controllers;

public class SmellController extends ActionController {

    private int radius;
    private int duration;

    public SmellController(int radius, int duration) {
        super(duration);
        this.ticksPre = -duration;
        this.radius = radius;
        this.duration = duration;
    }

    public void setRadiusAndDuration(int radius, int duration) {
        this.radius = radius;
        this.duration = duration;
    }

    public int getRadius() {
        return radius;
    }

    public int getDuration() {
        return duration;
    }
}
