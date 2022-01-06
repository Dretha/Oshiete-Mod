package com.dretha.drethamod.items.firearm;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBullet extends Item implements IHasModel {

    private final Bullets bullets;
    private EnumRarity rarity = EnumRarity.COMMON;

    public ItemBullet(String name, Bullets bullets) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        setMaxStackSize(64);
        this.bullets = bullets;

        InitItems.ITEMS.add(this);
    }
    public ItemBullet(String name, Bullets bullets, EnumRarity rarity) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        setMaxStackSize(64);
        this.bullets = bullets;
        this.rarity = rarity;

        InitItems.ITEMS.add(this);
    }

    public Bullets getBulletType() {
        return bullets;
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
