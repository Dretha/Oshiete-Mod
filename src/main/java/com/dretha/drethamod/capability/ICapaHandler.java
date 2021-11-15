package com.dretha.drethamod.capability;

import java.util.ArrayList;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.utils.enums.ImpactType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

public interface ICapaHandler {
	
	public void attachMaster(EntityLivingBase master);
	public EntityKagune getKagune();
	
	public String getModelLocation();
	public String getTextureLocation();
	public String getAnimationLocation();
	
	public void removeRCpoints(int points);
	public void addRCpoints(int points);
	public void setRCpoints(int points);
    public int getRCpoints();
    
    public void removeRClevel(int points);
	public void addRClevel(int points);
	public void setRClevel(int points);
	public void updateRClevel();
    public int getRClevel();
	 
	public boolean isGhoul();
	public void becomeGhoul(boolean isGhoul, int ghoulType, EntityLivingBase entity);
	public void becomeHuman(EntityLivingBase entity);
	public void setIsGhoul(boolean isGhoul);
    public int getGhoulType();
    public void setGhoulType(int ghoulType);
	 
	public int getGhoulRank();
	public int getDoveRank();
	
	public int getSkill();
	public void addSkill(int points);
	public void removeSkill(int points);
	public void setSkill(int points);
	
	public boolean isKaguneActive();
	public void setIsKaguneActive(boolean activeKagune);
	public void releaseKagune();
	public void admitKagune();
	
	public boolean isKakuganActive();
	public void setActivatedKakugan(boolean activeKagune);
	
	
	public boolean canKaguneActivated(int ticks);
	public void setKaguneActivatedTicksPre(int ticks);
	
	public boolean canSpeedModeActivated(int ticks);
	public void setSpeedModeTicksPre(int ticks);
	public boolean isSpeedModeActive();
	public void setActivatedSpeedMode(boolean b);
	
	public void setImpactModeTicksPre(int ticks);
	public int getImpactModeTicksPre();
	
	public ImpactType getImpactType();
	public void changeImpactType();
	
	public int getImpactSpeed();
	public void setImpactSpeed(int speed);
	
	public boolean canUseHit(int ticks);
	public boolean canAttackEntityFrom(int ticks);
	public void setHitTicksPre(int ticks);
	
	public int getShardCountInEntity();
	public void setShardCountInEntity(int count);
	public void addShardCountInEntity();
	
	public boolean isUkakuShooting();
	public void changeUkakuShooting();
	
	public boolean getSpawnKagunePatriclesFlag();
	public int getSpawnKagunePatriclesTicksPre();
	public void setSpawnKagunePatriclesFlag(boolean flag);
	
}

