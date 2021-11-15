package com.dretha.drethamod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapaProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(ICapaHandler.class)
	 public static final Capability<ICapaHandler> PLAYER_CAP = null;

	 private ICapaHandler instance = PLAYER_CAP.getDefaultInstance();

	 @Override
	 public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	 {
	 return capability == PLAYER_CAP;
	 }

	 @Override
	 public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	 {
	 return capability == PLAYER_CAP ? PLAYER_CAP.<T> cast(this.instance) : null;
	 }

	 @Override
	 public NBTBase serializeNBT()
	 {
	 return PLAYER_CAP.getStorage().writeNBT(PLAYER_CAP, this.instance, null);
	 }

	 @Override
	 public void deserializeNBT(NBTBase nbt)
	 {
		 PLAYER_CAP.getStorage().readNBT(PLAYER_CAP, this.instance, null, nbt);
	 }

}
