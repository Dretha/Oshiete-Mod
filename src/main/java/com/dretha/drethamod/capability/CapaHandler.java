package com.dretha.drethamod.capability;

import com.dretha.drethamod.utils.Randomizer;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.controllers.DelayActionController;
import com.dretha.drethamod.utils.controllers.NoParamActionController;
import com.dretha.drethamod.utils.controllers.SmellController;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CapaHandler implements ICapaHandler {

	private final PersonStats personStats = new PersonStats();
	private boolean isFirstJoin = true;

	private final int smellRadius = Randomizer.random(new Randomizer(1000, 2001, 0.01), new Randomizer(100, 201, 0.05), new Randomizer(50, 101, 0.14), new Randomizer(17, 26, 0.80));
	private final int smellDuration = smellRadius * 25;

	ActionController kaguneActivateController = new ActionController(20);
	ActionController speedModeController = new ActionController(20);
	ActionController forceSpeedController = new ActionController(10);
	DelayActionController forceThrustController = new DelayActionController(5, 45);
	ActionController impactModeController = new ActionController(3);
	ActionController shootController = new ActionController(3);
	NoParamActionController kuinkeSpeedController = new NoParamActionController();
	SmellController smellController = new SmellController(smellRadius, smellDuration);

	private int lastFoodAmount = 0;
	private Item lastUseItem = Items.AIR;
	private float lastExhaustion = 0;

	private float cameraOffset = 0;
	private boolean isCopyrightMode = false;
	private boolean isDarkeningKagune = true;

	@Override
	public PersonStats personStats() {
		return personStats;
	}


	@Override
	public int getDamage() {
		return personStats.getDamage();
	}


	@Override
	public boolean isGhoul() {
		return personStats.isGhoul();
	}

	@Override
	public ActionController getKaguneActivateController() {
		return kaguneActivateController;
	}

	@Override
	public ActionController getSpeedModeController() {
		return speedModeController;
	}

	@Override
	public ActionController getForceSpeedController() {
		return forceSpeedController;
	}

	@Override
	public DelayActionController getForceThrustController() {
		return forceThrustController;
	}

	@Override
	public ActionController getImpactModeController() {
		return impactModeController;
	}

	@Override
	public ActionController getShootController() {
		return shootController;
	}

	@Override
	public NoParamActionController getKuinkeSpeedController() {
		return kuinkeSpeedController;
	}

	@Override
	public SmellController getSmellController() {
		return smellController;
	}


	@Override
	public void setLastFoodAmount(int amount) {
		this.lastFoodAmount = amount;
	}

	@Override
	public int getLastFoodAmount() {
		return this.lastFoodAmount;
	}

	@Override
	public void setLastUseItem(Item item) {
		lastUseItem = item;
	}

	@Override
	public Item getLastUseItem() {
		return lastUseItem;
	}

	@Override
	public void setLastExhaustion(float lastExhaustion) {
		this.lastExhaustion = lastExhaustion;
	}

	@Override
	public float getLastExhaustion() {
		return lastExhaustion;
	}

	@Override
	public boolean isFirstJoin() {
		return isFirstJoin;
	}

	@Override
	public void setJoin() {
		isFirstJoin = false;
	}

	@Override
	public void setCameraOffset(float offset) {
		cameraOffset = offset;
	}

	@Override
	public float getCameraOffset() {
		return cameraOffset;
	}

	@Override
	public void setCopyrightMode(boolean b) {
		isCopyrightMode = b;
	}

	@Override
	public boolean isCopyrightMode() {
		return isCopyrightMode;
	}

	@Override
	public void setDarkeningKagune(boolean b) {
		isDarkeningKagune = b;
	}

	@Override
	public boolean isDarkeningKagune() {
		return isDarkeningKagune;
	}
}
