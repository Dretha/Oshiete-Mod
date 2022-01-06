package com.dretha.drethamod.capability.firearm;

import com.dretha.drethamod.items.firearm.Bullets;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CapaFirearmStorage implements Capability.IStorage<ICapaFirearmHandler> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICapaFirearmHandler> capability, ICapaFirearmHandler instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();

        compound.setInteger("ammo", instance.getAmmo());
        compound.setString("bullet", instance.getBullets().toString());

        return compound;
    }

    @Override
    public void readNBT(Capability<ICapaFirearmHandler> capability, ICapaFirearmHandler instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;

        instance.setAmmo(compound.getInteger("ammo"));
        instance.setBullets(Bullets.valueOf(compound.getString("bullet")));
    }
}
