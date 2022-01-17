package com.dretha.drethamod.utils;

import com.dretha.drethamod.main.Oshiete;

public class Randomizer {
    public final int from;
    public final int to;
    public final double chance;

    public Randomizer(int from, int to, double chance) {
        this.from = from;
        this.to = to;
        this.chance = chance;
    }

    /**
     * Принимает рандомайзеры-единицы, созданные по принципу:
     * from - число "от" включительно
     * to - число "до" невключительно
     * (промежуток в котором генерируется число)
     * chance - шанс выбора этой единицы среди других
     * Меньшие шансы нужно записывать первее!
     * Сумма шансов должна быть равна 1!
     * */
    public static int random(Randomizer... units)
    {
        if (checkChance(units)) return -1;

        do {
            for (Randomizer unit : units) {
                if (unit.chance >= Math.random()) {
                    return Oshiete.random.nextInt(unit.to - unit.from) + unit.from;
                }
            }
        }
        while (true);
    }

    private static boolean checkChance(Randomizer[] units) {
        float summ = 0;
        for (Randomizer unit : units) {
            summ += unit.chance;
        }
        if (summ!=1F)
            System.out.println("RandomizerError: incorrect chance data!");
        return summ!=1F;
    }
}
