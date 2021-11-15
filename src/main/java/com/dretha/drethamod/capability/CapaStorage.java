package com.dretha.drethamod.capability;

import java.util.ArrayList;

import com.dretha.drethamod.utils.enums.ImpactType;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapaStorage implements IStorage<ICapaHandler>{
	
	@Override
	public NBTBase writeNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side) {
	    final NBTTagCompound compound = new NBTTagCompound();
	    
	    compound.setBoolean("isGhoul", instance.isGhoul());
	    compound.setInteger("ghoulType", instance.getGhoulType());
	    compound.setInteger("RCpoints", instance.getRCpoints());
	    compound.setInteger("skillPoints", instance.getSkill());
	    compound.setBoolean("isKaguneActive", instance.isKaguneActive());
	    compound.setBoolean("isKakuganActive", instance.isKakuganActive());
	    compound.setInteger("shardCountInEntity", instance.getShardCountInEntity());
	    compound.setString("impactType", instance.getImpactType().toString());
	    
	    return compound;
	}

	@Override
	public void readNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound compound = (NBTTagCompound) nbt;
		
		instance.setIsGhoul(compound.getBoolean("isGhoul"));
		instance.setGhoulType(compound.getInteger("ghoulType"));
		instance.setRCpoints(compound.getInteger("RCpoints"));
		instance.setSkill(compound.getInteger("skillPoints"));
		instance.setIsKaguneActive(compound.getBoolean("isKaguneActive"));
		instance.setActivatedKakugan(compound.getBoolean("isKakuganActive"));
		instance.setShardCountInEntity(compound.getInteger("shardCountInEntity"));
		if (ImpactType.valueOf(compound.getString("impactType"))!=ImpactType.THRUST) instance.changeImpactType();
	}
	
}
