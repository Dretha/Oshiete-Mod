package com.dretha.drethamod.capability.firearm;

import com.dretha.drethamod.items.firearm.Bullets;

public interface ICapaFirearmHandler {

    int getAmmo();
    void setAmmo(int ammo);
    void setAmmo(int ammo, Bullets bullets);
    void spendAmmo();

    Bullets getBullets();
    void setBullets(Bullets bullets);
}
