package com.dretha.drethamod.items;

import javax.annotation.Nullable;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IKagune;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKaguneBikaku extends KaguneMelee implements IKagune {
	
	public ItemKaguneBikaku (String name, float attackDamage, float speedDamage) {
		this.maxStackSize = 1;
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
		setRegistryName(name);
        setUnlocalizedName(name);
        this.attackDamage=attackDamage;
        this.speedDamage=speedDamage;
        
        setCreativeTab(ModCreativeTab.CTAB);
        InitItems.ITEMS.add(this);
	}
	
}
