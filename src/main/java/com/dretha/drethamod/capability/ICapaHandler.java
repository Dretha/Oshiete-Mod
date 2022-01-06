package com.dretha.drethamod.capability;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.HandType;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public interface ICapaHandler {
	
	public void attachMaster(EntityPlayer master);
	public EntityKagune getKagune();
	public void nullKagune();
	
	public void copyInventory(ICapaHandler capa);
    public ClothesInventory getInventory();
	
	public String getModelLocation();
	public String getTextureLocation();
	public String getAnimationLocation();
	public int getModelVariant();
	public int getTextureVariant();
	
	public String getEnumId();
	
	public void removeRCpoints(int points);
	public void addRCpoints(int points);
	public void setRCpoints(int points);
    public int getRCpoints();
    
    public void removeRClevel(int points);
	public void addRClevel(int points);
	public void setRClevel(int points);
	public void updateRClevel();
    public int getRClevel();
    public boolean RClevelFull();
	 
	public boolean isGhoul();
	public void becomeGhoul(GhoulType ghoulType, EntityLivingBase entity);
	public void becomeHuman(EntityLivingBase entity);
	public void setIsGhoul(boolean isGhoul);
	public void updateSpeedAttribute();
	public void applyAtrSpeedMode();
	public void removeAtrSpeedMode();
	
    public GhoulType getGhoulType();
    public void setGhoulType(@Nonnull GhoulType ghoulType);
    public UkakuState ukakuState();
    public void setUkakuState(UkakuState state);
    public HandType handType();
    public void setHandType(HandType type);
    public boolean rightHanded();
    
    public boolean ukaku();
    
	public int rank();
	
	public int getSkill();
	public void addSkill(int points);
	public void removeSkill(int points);
	public void setSkill(int points);
	
	public boolean isKaguneActive();
	public void setIsKaguneActive(boolean activeKagune);
	public void releaseKagune();
	public void admitKagune();
	public void updateEntityKagune();
	
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
	
	public boolean isBlock();
	public void setBlock(boolean b);
	public float getResponseValue();
	
	public ImpactType getImpactType();
	public void changeImpactType();
	
	public int getShardCountInEntity();
	public void setShardCountInEntity(int count);
	public void addShardCountInEntity();
	
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
}

