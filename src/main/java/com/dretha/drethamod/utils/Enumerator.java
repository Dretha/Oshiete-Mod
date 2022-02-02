package com.dretha.drethamod.utils;

import com.dretha.drethamod.main.Oshiete;

public class Enumerator {

    private int currentNumber = 1;
    private final int count;
    private int ticksPre = 0;

    public Enumerator(int count) {
        this.count = count;
    }

    public void recite() {
        currentNumber++;
        if (currentNumber > count)
            currentNumber = 1;
    }
    public void recite(int ticks) {
        if (ticksPre+3<ticks) {
            ticksPre = ticks;
            currentNumber++;
            if (currentNumber > count)
                currentNumber = 1;
        }
    }

    public int number() {
        return currentNumber;
    }

    public void randomize() {
        currentNumber = Oshiete.random.nextInt(count) + 1;
    }
}
