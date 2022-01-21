package com.dretha.drethamod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapaStorage implements IStorage<ICapaHandler>{
	
	@Override
	public NBTBase writeNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side)
	{
	    final NBTTagCompound compound = new NBTTagCompound();

		instance.personStats().writeToNBT(compound);
	    
	    return compound;
	}

	@Override
	public void readNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side, NBTBase nbt)
	{
		final NBTTagCompound compound = (NBTTagCompound) nbt;

		instance.personStats().readFromNBT(compound);
	}
}
