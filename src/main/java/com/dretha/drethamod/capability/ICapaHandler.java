package com.dretha.drethamod.capability;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.controllers.DelayActionController;
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
	ActionController getForceSpeedController();
	DelayActionController getForceThrustController();
	
	void setImpactModeTicksPre(int ticks);
	int getImpactModeTicksPre();

	int getDamage();
	
	boolean getSpawnKagunePatriclesFlag();
	int getSpawnKagunePatriclesTicksPre();
	void setSpawnKagunePatriclesFlag(boolean flag);
	
	void setLastFoodAmount(int amount);
	int getLastFoodAmount();
	
	int getShootTicksPre();
	void setShootTicksPre(int ticks);
	
	int getSmellRadius();
	int getSmellDuration();
	int getSmellTicksPre();
	void setSmellTicksPre(int ticks);

	int getAttackKuinkeTicksPre();
	void setAttackKuinkeTicksPre(int ticks);
}

