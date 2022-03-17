package com.dretha.drethamod.capability;

import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.controllers.DelayActionController;
import com.dretha.drethamod.utils.controllers.NoParamActionController;
import com.dretha.drethamod.utils.controllers.SmellController;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.item.Item;

public interface ICapaHandler {

	PersonStats personStats();
	
	ActionController getKaguneActivateController();
	ActionController getSpeedModeController();
	ActionController getForceSpeedController();
	DelayActionController getForceThrustController();
	ActionController getImpactModeController();
	ActionController getShootController();
	NoParamActionController getKuinkeSpeedController();
	SmellController getSmellController();

	int getDamage();
	boolean isGhoul();

	void setLastFoodAmount(int amount);
	int getLastFoodAmount();
	void setLastUseItem(Item item);
	Item getLastUseItem();
	void setLastExhaustion(float lastExhaustion);
	float getLastExhaustion();

	boolean isFirstJoin();
	void setJoin();

	void setCameraOffset(float offset);
	float getCameraOffset();

	void setCopyrightMode(boolean b);
	boolean isCopyrightMode();

	void setDarkeningKagune(boolean b);
	boolean isDarkeningKagune();
}

