package com.dretha.drethamod.items;

public enum Tools {

    HAMMER(3, 3, true);


    private final int damage;
    private final float hardness;
    public final boolean isContainerItem;

    Tools(int damage, float hardness, boolean isContainerItem) {
        this.damage = damage;
        this.hardness = hardness;
        this.isContainerItem = isContainerItem;
    }

    public int getDamage(int damage1) {
        return damage * damage1;
    }

    public int getHardness(int maxUses) {
        return (int) (maxUses/hardness);
    }
}
