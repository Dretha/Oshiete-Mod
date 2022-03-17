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
		compound.setInteger("smellRadius", instance.getSmellController().getRadius());
		compound.setInteger("smellDuration", instance.getSmellController().getDuration());
		compound.setBoolean("isFirstJoin", instance.isFirstJoin());
		compound.setFloat("cameraOffset", instance.getCameraOffset());
		compound.setBoolean("isCopyrightMode" ,instance.isCopyrightMode());
		compound.setBoolean("isDarkeningKagune" ,instance.isDarkeningKagune());
	    
	    return compound;
	}

	@Override
	public void readNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side, NBTBase nbt)
	{
		final NBTTagCompound compound = (NBTTagCompound) nbt;

		instance.personStats().readFromNBT(compound);
		instance.getSmellController().setRadiusAndDuration(compound.getInteger("smellRadius"), compound.getInteger("smellDuration"));
		if (!compound.getBoolean("isFirstJoin"))
			instance.setJoin();
		instance.setCameraOffset(compound.getFloat("cameraOffset"));
		instance.setCopyrightMode(compound.getBoolean("isCopyrightMode"));
		instance.setDarkeningKagune(compound.getBoolean("isDarkeningKagune"));
	}
}
