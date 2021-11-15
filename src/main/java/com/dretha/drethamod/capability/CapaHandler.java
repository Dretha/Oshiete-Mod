package com.dretha.drethamod.capability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.main.Main;
import com.dretha.drethamod.utils.enums.ImpactType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import scala.actors.threadpool.Arrays;
import scala.collection.Map;

public class CapaHandler implements ICapaHandler {
	
	Random random = new Random();
	
	//main ghoul variables
	private int RCpoints = random.nextInt(501)+200;
	private int RClevel = RCpoints/10;
	private boolean isGhoul = false;
	private int ghoulType = 0;
	
	//less main
	private EntityLivingBase master = null;
	private EntityKagune entityKagune;
	
	private int MODEL_VARIANT = 1;
	private int TEXTURE_VARIANT = 1;
	
	private final int toGhoulType = 5;
	private int skillPoints = 0;
	
	private ImpactType impactType = ImpactType.THRUST;
	private int impactSpeed = 10;
	
	private int kaguneActivatedTicksGap = 20;
	private int kaguneActivatedTicksPre = 0;
	
	private int impactModeTicksPre = 0;
	
	private int speedModeTicksGap = 20;
	private int speedModeTicksPre = 0;
	private boolean isSpeedModeActive = false;
	
	private int thrustTicksPre = 0;
	
	private boolean isKaguneActive = false;
	private boolean isKakuganActive = false;
	private boolean ukakuShooting = false;
	
	private int shardCountInEntity = 0;
	
	private int spawnKagunePatriclesTicksPre = 0;
	private boolean spawnKagunePatriclesFlag = false;
	
	@Override
	public void attachMaster(EntityLivingBase master) {
		this.master = master;
	}
	
	@Override
	public EntityKagune getKagune() {
		return entityKagune;
	}
	
	
	
	@Override
	public String getModelLocation() {
		return String.format("geo/kagune/kagune%d%02d.geo.json", getNNGT(), this.MODEL_VARIANT);
	}

	@Override
	public String getTextureLocation() {
		return String.format("textures/entity/kagune/kagune%d%02d%d.geo.json", getNNGT(), this.MODEL_VARIANT, this.TEXTURE_VARIANT);
	}

	@Override
	public String getAnimationLocation() {
		return String.format("animations/kagune/kagune%d%02d.animation.json", getNNGT(), this.MODEL_VARIANT);
	}
	
	private int getNNGT() {
		return this.getGhoulType() == 0 ? 2 : this.getGhoulType();
	}
	
	
	
	@Override
	public ImpactType getImpactType() {
		return impactType;
	}
	@Override
	public void changeImpactType() {
		this.impactType = this.impactType.change();
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
	public int getImpactSpeed() {
		return this.impactSpeed;
	}
	@Override
	public void setImpactSpeed(int speed) {
		this.impactSpeed = speed;
	}
	
	
	
	@Override
	public void removeRCpoints(int points) {
		this.RCpoints -= points;
		 
		 if (this.RCpoints < 0) this.RCpoints = 0;
		
	}

	@Override
	public void addRCpoints(int points) {
		this.RCpoints += points;		
	}

	@Override
	public void setRCpoints(int points) {
		this.RCpoints = points;		
	}

	@Override
	public int getRCpoints() {
		return this.RCpoints;
	}

	
	
	
	@Override
	public void removeRClevel(int points) {
		this.RClevel = (this.RCpoints/10 - (this.RCpoints/10 - this.RClevel)) - points;
		if (this.RClevel<0) this.RClevel=0;
	}

	@Override
	public void addRClevel(int points) {
		this.RClevel = (this.RCpoints/10 - (this.RCpoints/10 - this.RClevel)) + points;	
		if (this.RClevel>this.RCpoints/10) this.RClevel=this.RCpoints/10;
	}

	@Override
	public void updateRClevel() {
		this.RClevel = this.RCpoints/10 - (this.RCpoints/10 - this.RClevel);
	}
	
	@Override
	public void setRClevel(int points) {
		this.RClevel=points;
	}

	@Override
	public int getRClevel() {
		return this.RClevel;
	}
	
	
	
	
	@Override
	public boolean isGhoul() {
		return this.isGhoul;
	}

	@Override
	public void becomeGhoul(boolean isGhoul, int ghoulType, EntityLivingBase entity) {
		if (isGhoul && ghoulType>0 && ghoulType<toGhoulType && !this.isGhoul) {
			this.isGhoul = isGhoul;
			this.ghoulType = ghoulType;
			this.RCpoints+=801;
			updateRClevel();
			entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
			entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
			
		}
	}

	@Override
	public void becomeHuman(EntityLivingBase entity) {
		if (this.isGhoul) {
			this.setIsGhoul(false);
			Random random = new Random();
			this.setRCpoints(random.nextInt(501)+200);
			this.updateRClevel();
			this.setGhoulType(0);
		} else if (entity instanceof EntityPlayer){
			((EntityPlayer) entity).sendMessage(new TextComponentString("You are already human."));
		}
	}
	
	//Ќе использовать если это не чтение данных
	@Override
	public void setIsGhoul(boolean isGhoul) {
		this.isGhoul=isGhoul;
	}

	@Override
	public int getGhoulType() {
		return this.ghoulType;
	}
	
	@Override
	public void setGhoulType(int ghoulType) {
		this.ghoulType=ghoulType;
	}

	
	
	
	@Override
	public int getGhoulRank() {
		if (RCpoints>999 && RCpoints<2000) {
			return 1;
		}
		if (RCpoints>999 && RCpoints<2000) {
			return 2;
		}
		if (RCpoints>1999 && RCpoints<3000) {
			return 3;
		}
		if (RCpoints>2999 && RCpoints<4000) {
			return 4;
		}
		return 0;
	}

	@Override
	public int getDoveRank() {
		// TODO јвтоматически созданна€ заглушка метода
		return 0;
	}
	
	
	
	
	@Override
	public int getSkill() {
		return this.skillPoints;
	}
	
	@Override
	public void setSkill(int points) {
		this.skillPoints = points;
	}
	
	@Override
	public void addSkill(int points) {
		this.skillPoints+=points;
	}

	@Override
	public void removeSkill(int points) {
		this.skillPoints-=points;
		if (this.skillPoints < 0) this.skillPoints = 0;
	}

	
	
	
	
	
	@Override
	public boolean isKaguneActive() {
		return this.isKaguneActive;
	}
	
	@Override
	public void setIsKaguneActive(boolean activeKagune) {
		this.isKaguneActive = activeKagune;
	}

	AttributeModifier speedghoul = new AttributeModifier("speedghoul", 0.5, 2);
	
	
	@Override
	public void releaseKagune() {
		this.spawnKagunePatriclesTicksPre = master.ticksExisted;
		this.setSpawnKagunePatriclesFlag(true);
		//Main.NETWORK.sendToAllAround(new MyMessage(master.posX, master.posY, master.posZ), new NetworkRegistry.TargetPoint(master.world.provider.getDimension(), master.posX, master.posY, master.posZ, 60));
		master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedghoul);
		
		this.isKaguneActive = true;
		this.isKakuganActive = true;

		entityKagune=new EntityKagune(master);
		entityKagune.getThreadLivingUpdate().start();
		
		if (master instanceof EntityPlayer) {
		    ((EntityPlayer) master).world.playSound(null, ((EntityPlayer) master).getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
		}
	}
	
	@Override
	public void admitKagune() {
		master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedghoul.getID()));
		this.isKaguneActive = false;
		if (!this.isSpeedModeActive)
		this.isKakuganActive = false;
		
		entityKagune.getThreadLivingUpdate().interrupt();
		if (entityKagune!=null) entityKagune.setDead();
		entityKagune=null;
	}

	
	
	
	
	
	
	@Override
	public boolean canKaguneActivated(int ticks) {
		return this.kaguneActivatedTicksPre + this.kaguneActivatedTicksGap <= ticks;
	}

	@Override
	public void setKaguneActivatedTicksPre(int ticks) {
		this.kaguneActivatedTicksPre = ticks;
	}
	
	
	
	
	@Override
	public boolean canUseHit(int ticks) {
		return this.thrustTicksPre + this.impactType.speed(this) <= ticks;
	}
	
	@Override
	public boolean canAttackEntityFrom(int ticks) {
		return this.thrustTicksPre + (this.impactType.speed(this)/2) == ticks;
	}

	@Override
	public void setHitTicksPre(int ticks) {
		this.thrustTicksPre = ticks;
	}
	
	
	
	
	
	@Override
	public int getShardCountInEntity() {
		return this.shardCountInEntity;
	}

	@Override
	public void addShardCountInEntity() {
		this.shardCountInEntity++;
	}
	
	@Override
	public void setShardCountInEntity(int count) {
		this.shardCountInEntity = count;
	}
	
	
	

	@Override
	public boolean isUkakuShooting() {
		return this.ukakuShooting;
	}

	@Override
	public void changeUkakuShooting() {
		this.ukakuShooting=!this.ukakuShooting;
	}

	
	
	
	
	@Override
	public boolean isKakuganActive() {
		return this.isKakuganActive;
	}

	@Override
	public void setActivatedKakugan(boolean activeKakugan) {
		this.isKakuganActive = activeKakugan;
	}


	
	
	
	
	@Override
	public boolean canSpeedModeActivated(int ticks) {
		return this.speedModeTicksPre + this.speedModeTicksGap <= ticks;
	}

	@Override
	public void setSpeedModeTicksPre(int ticks) {
		this.speedModeTicksPre=ticks;
	}

	@Override
	public boolean isSpeedModeActive() {
		return this.isSpeedModeActive;
	}

	@Override
	public void setActivatedSpeedMode(boolean b) {
		this.isSpeedModeActive = b;
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

	
	
}
