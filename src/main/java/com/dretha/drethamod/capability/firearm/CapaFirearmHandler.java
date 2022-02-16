package com.dretha.drethamod.capability.firearm;

import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.items.firearm.ItemMagazine;

public class CapaFirearmHandler implements ICapaFirearmHandler {

    private int ammo = 0;
    private Bullets bullets = Bullets.NONE;
// TODO добавить капу к магазину
    @Override
    public int getAmmo() {
        return ammo;
    }

    @Override
    public void setAmmo(int ammo) {
        this.ammo = Math.min(ammo, ItemMagazine.STANDART_MAGAZINE_CAPACITY);
    }

    @Override
    public void setAmmo(int ammo, Bullets bullets) {
        this.ammo = ammo;
        this.bullets = bullets;
    }

    @Override
    public void spendAmmo() {
        if (ammo>0)
            ammo--;
        if (ammo==0)
            bullets = Bullets.NONE;
    }



    @Override
    public Bullets getBullets() {
        return bullets;
    }

    @Override
    public void setBullets(Bullets bullets) {
        this.bullets = bullets;
    }
}
