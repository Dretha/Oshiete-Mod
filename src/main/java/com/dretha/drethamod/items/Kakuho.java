package com.dretha.drethamod.items;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.utils.enums.GhoulType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Kakuho extends ItemGhoulFood {

    private final GhoulType ghoulType;

    public Kakuho(String name, String description, GhoulType ghoulType, int amount, float saturation) {
        super(name, description, amount, saturation, 1024, 128, false);
        this.ghoulType = ghoulType;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("RCpoints", satiation);
        stack.setTagCompound(compound);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!stack.hasTagCompound()) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("RCpoints", satiation);
            stack.setTagCompound(compound);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, desc, flagIn);
        if (!stack.hasTagCompound()) return;
        int RCpoints = stack.getTagCompound().getInteger("RCpoints");
        desc.add(RCpoints + " RC Cells");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumKeeper.HIGH_SATIATION;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            if (stack.hasTagCompound()) {
                NBTTagCompound compound = stack.getTagCompound();
                ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
                satiation = compound.getInteger("RCpoints");
                if (capa.isGhoul()) {
                    capa.addRCpoints((int) ((float) satiation / 7.8125F));
                    if (capa.getGhoulType() == ghoulType)
                        capa.addRCpoints((int) ((float) satiation / 7.8125F));
                }
            }
        }
    }

    public GhoulType getGhoulType() {
        return ghoulType;
    }
}
