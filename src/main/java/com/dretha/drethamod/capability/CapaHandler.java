package com.dretha.drethamod.capability;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.Randomizer;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.GrowthStages;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;
import java.util.Random;

public class CapaHandler implements ICapaHandler {
	
	//main ghoul variables
	private final PersonStats personStats = new PersonStats();
	
	//less main
	ActionController kaguneActivateController = new ActionController(20);
	ActionController speedModeController = new ActionController(20);
	ActionController forceSpeedController = new ActionController(10);
	
	private int impactModeTicksPre = 0;

	
	private int spawnKagunePatriclesTicksPre = 0;
	private boolean spawnKagunePatriclesFlag = false;
	
	private int lastFoodAmount = 0;
	
	private int shootTicksPre = 0;
	
	private int smellTicksPre = -1000;
	public final int smellMult = 25;
	private int smellRadius = Randomizer.random(new Randomizer(1000, 2001, 0.01), new Randomizer(100, 201, 0.05), new Randomizer(50, 101, 0.14), new Randomizer(17, 26, 0.80));
	private int smellDuration = smellRadius * smellMult;

	private int attackKuinkeTicksPre = 0;

	@Override
	public PersonStats personStats() {
		return personStats;
	}
	
	
	@Override
	public void setImpactModeTicksPre(int ticks) {
		this.impactModeTicksPre = ticks;
	}


	@Override
	public int getImpactModeTicksPre() {
		return this.impactModeTicksPre;
	}



	@Override
	public int getDamage() {
		int damage = personStats.getRCpoints()/100;
		return damage;
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
	public boolean getSpawnKagunePatriclesFlag() {
		return this.spawnKagunePatriclesFlag;
	}
	@Override
	public int getSpawnKagunePatriclesTicksPre() {
		return this.spawnKagunePatriclesTicksPre;
	}
	@Override
	public void setSpawnKagunePatriclesFlag(boolean flag) {
		this.spawnKagunePatriclesFlag = flag;
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
	public int getShootTicksPre() {
		return this.shootTicksPre;
	}

	@Override
	public void setShootTicksPre(int ticks) {
		this.shootTicksPre = ticks;
	}
	
	

	@Override
	public int getSmellRadius() {
		return smellRadius;
	}
	
	@Override
	public int getSmellDuration() {
		return smellDuration;
	}

	@Override
	public int getSmellTicksPre() {
		return smellTicksPre;
	}

	@Override
	public void setSmellTicksPre(int ticks) {
		smellTicksPre = ticks;
	}


	@Override
	public int getAttackKuinkeTicksPre() {
		return attackKuinkeTicksPre;
	}

	@Override
	public void setAttackKuinkeTicksPre(int ticks) {
		this.attackKuinkeTicksPre = ticks;
	}


}
