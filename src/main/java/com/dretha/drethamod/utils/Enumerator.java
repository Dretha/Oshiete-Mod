package com.dretha.drethamod.utils;

import com.dretha.drethamod.main.Oshiete;

public class Enumerator {

    private int currentNumber;
    private final int to;
    private final int of;
    private int ticksPre = 0;

    public Enumerator(int of, int to) {
        this.to = to;
        this.of = of;
        this.currentNumber = of;
    }

    public void forward() {
        currentNumber++;
        if (currentNumber >= to)
            currentNumber = of;
    }
    public void forwardOnce(int ticks) {
        if (ticksPre+2<ticks) {
            ticksPre = ticks;
            currentNumber++;
            if (currentNumber >= to)
                currentNumber = 1;
        }
    }

    public void back() {
        currentNumber--;
        if (currentNumber < of)
            currentNumber = to-1;
    }

    public int number() {
        return currentNumber;
    }

    public void randomize() {
        currentNumber = Oshiete.random.nextInt(to) + of;
    }

    public void reset() {
        currentNumber = of;
    }
}
