package com.dretha.drethamod.utils.pojo;

import com.dretha.drethamod.main.Oshiete;

public class SimpleColor {
    public final int red;
    public final int green;
    public final int blue;

    public SimpleColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static SimpleColor randomColor() {
        return new SimpleColor(Oshiete.random.nextInt(241)+15, Oshiete.random.nextInt(241)+15, Oshiete.random.nextInt(241)+15);
    }
}
