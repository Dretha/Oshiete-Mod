package com.dretha.drethamod.capability.firearm;

import com.dretha.drethamod.capability.ICapaHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapaFirearmProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ICapaFirearmHandler.class)
    public static final Capability<ICapaFirearmHandler> FIREARM_CAP = null;

    private final ICapaFirearmHandler instance = FIREARM_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == FIREARM_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == FIREARM_CAP ? FIREARM_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return FIREARM_CAP.getStorage().writeNBT(FIREARM_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        FIREARM_CAP.getStorage().readNBT(FIREARM_CAP, this.instance, null, nbt);
    }

}
