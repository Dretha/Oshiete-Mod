package com.dretha.drethamod.capability.world;

import com.dretha.drethamod.capability.firearm.ICapaFirearmHandler;
import com.dretha.drethamod.items.firearm.Bullets;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class WorldCapaStorage implements Capability.IStorage<IWorldCapaHandler> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IWorldCapaHandler> capability, IWorldCapaHandler instance, EnumFacing side) {
        final NBTTagCompound compound = new NBTTagCompound();

        instance.getHeadquartersCCG().writeToNBT(compound);

        return compound;
    }

    @Override
    public void readNBT(Capability<IWorldCapaHandler> capability, IWorldCapaHandler instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound compound = (NBTTagCompound) nbt;

        instance.getHeadquartersCCG().readFromNBT(compound);
    }
}
