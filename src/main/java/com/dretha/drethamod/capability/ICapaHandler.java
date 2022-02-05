package com.dretha.drethamod.capability;

import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.controllers.DelayActionController;
import com.dretha.drethamod.utils.controllers.NoParamActionController;
import com.dretha.drethamod.utils.controllers.SmellController;
import com.dretha.drethamod.utils.stats.PersonStats;

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
}

