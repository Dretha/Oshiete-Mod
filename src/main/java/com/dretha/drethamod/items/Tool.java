package com.dretha.drethamod.items;

public enum Tool {

    HAMMER(3, 3);


    private final int damage;
    private final float hardness;

    Tool(int damage, float hardness) {
        this.damage = damage;
        this.hardness = hardness;
    }

    public int getDamage(int damage1) {
        return damage * damage1;
    }

    public int getHardness(int maxUses) {
        return (int) (maxUses/hardness);
    }
}
