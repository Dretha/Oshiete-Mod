package com.dretha.drethamod.capability.world;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class WorldCapaProvider  implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IWorldCapaHandler.class)
    public static final Capability<IWorldCapaHandler> WORLD_CAP = null;

    private final IWorldCapaHandler instance = WORLD_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == WORLD_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == WORLD_CAP ? WORLD_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return WORLD_CAP.getStorage().writeNBT(WORLD_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        WORLD_CAP.getStorage().readNBT(WORLD_CAP, this.instance, null, nbt);
    }

}
