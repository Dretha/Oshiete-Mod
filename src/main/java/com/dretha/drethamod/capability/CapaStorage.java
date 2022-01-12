package com.dretha.drethamod.capability;

import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapaStorage implements IStorage<ICapaHandler>{
	
	@Override
	public NBTBase writeNBT(Capability<ICapaHandler> capability, ICapaHandler instance, EnumFacing side) {
	    final NBTTagCompound compound = new NBTTagCompound();
	    
	    instance.getInventory().writeToNBT(compound);
	    
	    compound.setBoolean("isGhoul", instance.isGhoul());
	    compound.setString("ghoulType", instance.getGhoulType().toString());
	    compound.setString("ukakuState", instance.ukakuState().toString());
	    compound.setInteger("RCpoints", instance.getRCpoints());
	    compound.setInteger("RClevel", instance.getRClevel());
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
		
		instance.getInventory().readFromNBT(compound);
		
		instance.setIsGhoul(compound.getBoolean("isGhoul"));
		instance.setGhoulType(GhoulType.valueOf(compound.getString("ghoulType")));
		instance.setUkakuState(UkakuState.valueOf(compound.getString("ukakuState")));
		instance.setRCpoints(compound.getInteger("RCpoints"));
		instance.setRClevel(compound.getInteger("RClevel"));
		instance.setSkill(compound.getInteger("skillPoints"));
		instance.setIsKaguneActive(compound.getBoolean("isKaguneActive"));
		instance.setActivatedKakugan(compound.getBoolean("isKakuganActive"));
		instance.setShardCountInEntity(compound.getInteger("shardCountInEntity"));
		if (ImpactType.valueOf(compound.getString("impactType"))!=ImpactType.THRUST) instance.changeImpactType();
	}
	
}
