package com.dretha.drethamod.capability;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.GrowthStages;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public interface ICapaHandler {

	PersonStats personStats();
	boolean isGhoul();
	
	ActionController getKaguneActivateController();
	ActionController getSpeedModeController();
	
	void setImpactModeTicksPre(int ticks);
	int getImpactModeTicksPre();

	int getDamage();
	
	public boolean getSpawnKagunePatriclesFlag();
	public int getSpawnKagunePatriclesTicksPre();
	public void setSpawnKagunePatriclesFlag(boolean flag);
	
	public void setLastFoodAmount(int amount);
	public int getLastFoodAmount();
	
	public int getShootTicksPre();
	public void setShootTicksPre(int ticks);
	
	public int getSmellRadius();
	public int getSmellDuration();
	public int getSmellTicksPre();
	public void setSmellTicksPre(int ticks);

	int getAttackKuinkeTicksPre();
	void setAttackKuinkeTicksPre(int ticks);
}

