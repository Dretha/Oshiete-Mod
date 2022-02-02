package com.dretha.drethamod.items.firearm;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.client.resources.I18n;

public enum Bullets {
    NONE(0, false, I18n.format("desk.none"), (ItemBullet)InitItems.STEEL_BULLET),
    IRON(2, false, I18n.format("desk.ironbullets"), (ItemBullet)InitItems.IRON_BULLET),
    STEEL(3, false, I18n.format("desk.steelbullets"), (ItemBullet)InitItems.STEEL_BULLET),
    Q_STEEL(3, true, I18n.format("desk.qbullets"), (ItemBullet)InitItems.Q_BULLET);

    private final int damage;
    private final boolean hurtGhoul;
    private final String description;
    private final ItemBullet itemBullet;

    Bullets(int damage, boolean hurtGhoul, String description, ItemBullet itemBullet) {
        this.damage = damage;
        this.hurtGhoul = hurtGhoul;
        this.description = description;
        this.itemBullet = itemBullet;
    }

    public int getDamage() {
        return damage;
    }
    public boolean hurtGhoul() {
        return hurtGhoul;
    }
    public String getDescription() {
        return description;
    }
    public ItemBullet getItemBullet() {
        return itemBullet;
    }
}
