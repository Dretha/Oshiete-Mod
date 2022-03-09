package com.dretha.drethamod.capability.firearm;

import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.items.firearm.ItemMagazine;

public class CapaFirearmHandler implements ICapaFirearmHandler {

    private int ammo = 0;
    private Bullets bullets = Bullets.NONE;

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
    public void addAmmo(Bullets bullets) {
        if (bullets==this.bullets || isEmpty()) {
            ammo = Math.min(ammo + 1, ItemMagazine.STANDART_MAGAZINE_CAPACITY);
            this.bullets = bullets;
        }
    }


    @Override
    public Bullets getBullets() {
        return bullets;
    }

    @Override
    public void setBullets(Bullets bullets) {
        this.bullets = bullets;
    }

    @Override
    public boolean isEmpty() {
        return bullets==Bullets.NONE || ammo==0;
    }
}
