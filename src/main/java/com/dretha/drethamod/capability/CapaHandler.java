package com.dretha.drethamod.capability;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
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
	
	Random random = new Random();
	
	//main ghoul variables
	private int RCpoints = random.nextInt(501)+200;
	private int RClevel = RCpoints/10;
	private boolean isGhoul = false;
	private boolean isDove = false;
	private GhoulType ghoulType = GhoulType.NONE;
	private UkakuState ukakuState = UkakuState.NONE;
	
	//less main
	private EntityPlayer master = null;
	private EntityKagune entityKagune = null;
	
	private final ClothesInventory inventory = new ClothesInventory();
	
	private int MODEL_VARIANT = 1;
	private int TEXTURE_VARIANT = 2;
	
	private int skillPoints = 0;
	
	private ImpactType impactType = ImpactType.THRUST;
	
	private int kaguneActivatedTicksGap = 20;
	private int kaguneActivatedTicksPre = 0;
	
	private int impactModeTicksPre = 0;
	
	private int speedModeTicksGap = 20;
	private int speedModeTicksPre = 0;
	private boolean isSpeedModeActive = false;
	
	private int thrustTicksPre = 0;
	
	private boolean isBlock = false;
	
	private boolean isKaguneActive = false;
	private boolean isKakuganActive = false;
	
	private int shardCountInEntity = 0;
	
	private int spawnKagunePatriclesTicksPre = 0;
	private boolean spawnKagunePatriclesFlag = false;
	
	private int lastFoodAmount = 0;
	
	private int shootTicksPre = 0;
	
	private int smellTicksPre = -1000;
	private int smellDuration = 400;
	private int smellRadius = 20;

	private int attackKuinkeTicksPre = 0;
	
	@Override
	public void attachMaster(EntityPlayer master) {
		this.master = master;
	}
	
	@Override
	public EntityKagune getKagune() {
		return entityKagune;
	}
	public void nullKagune() {
		entityKagune = null;
	}
	
	
	
	@Override
	public String getModelLocation() {
		return String.format("geo/kagune/kagune%d%02d.geo.json", getNNGT(), this.MODEL_VARIANT);
	}

	@Override
	public String getTextureLocation() {
		return String.format("textures/entity/kagune/kagune%d%02d%02d.png", getNNGT(), this.MODEL_VARIANT, this.TEXTURE_VARIANT);
	}

	@Override
	public String getAnimationLocation() {
		return String.format("animations/kagune/kagune%d%02d.animation.json", getNNGT(), this.MODEL_VARIANT);
	}
	
	@Override
	public int getModelVariant() {
		return this.MODEL_VARIANT;
	}
	
	@Override
	public int getTextureVariant() {
		return this.TEXTURE_VARIANT;
	}
	
	private int getNNGT() {
		return this.getGhoulType() == null ? 2 : GhoulType.indexOf(this.getGhoulType())+1;
	}
	
	
	
	public String getEnumId() {
		return String.format("KAGUNE%d%02d", this.getGhoulType().id(), this.getModelVariant());
	}
	
	
	

    @Override
    public ClothesInventory getInventory(){
        return this.inventory;
    }

    @Override
    public void copyInventory(ICapaHandler capa) {
        this.inventory.copy(capa.getInventory());
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
	public boolean isBlock() {
		return this.isBlock;
	}
	
	@Override
	public void setBlock(boolean b) {
		this.isBlock = b;
	}



	@Override
	public int getDamage() {
		int damage = RCpoints/100;
		return damage;
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
		updateRClevel();
		this.RClevel -= points;
		if (this.RClevel<0) this.RClevel=0;
	}

	@Override
	public void addRClevel(int points) {
		updateRClevel();
		this.RClevel += points;	
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
	public boolean RClevelFull() {
		return this.RClevel == this.RCpoints/10;
	}
	
	
	
	
	@Override
	public boolean isGhoul() {
		return this.isGhoul;
	}

	@Override
	public void becomeGhoul(GhoulType ghoulType, EntityLivingBase entity) {
		this.isGhoul = true;
		this.ghoulType = ghoulType;
		if (ghoulType==GhoulType.UKAKU)
			this.ukakuState = UkakuState.generateState();
		this.RCpoints+=801;
		updateRClevel();
		entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
		entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
		entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
		entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
	}

	@Override
	public void becomeHuman(EntityLivingBase entity) {
			this.setIsGhoul(false);
			Random random = new Random();
			this.setRCpoints(random.nextInt(501)+200);
			this.updateRClevel();
			this.setGhoulType(GhoulType.NONE);
			entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
			entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0);
			entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
		
			((EntityPlayer) entity).sendMessage(new TextComponentString("You are already human."));
		
	}
	
	//Ќе использовать если это не чтение данных
	@Override
	public void setIsGhoul(boolean isGhoul) {
		this.isGhoul=isGhoul;
	}
	
	@Override
	public void updateSpeedAttribute() {
		IAttributeInstance ins = master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		ins.removeAllModifiers();
		if (this.isKaguneActive) ins.applyModifier(speedghoul);
		if (this.isSpeedModeActive) ins.applyModifier(speedmode);
	}
	
	AttributeModifier speedmode = new AttributeModifier("speedmode", 1.6, 2);
	@Override
	public void applyAtrSpeedMode() {
		master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedmode);
	}
	
	@Override
	public void removeAtrSpeedMode() {
		master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedmode);
	}
	
	
	

	@Override
	public GhoulType getGhoulType() {
		return this.ghoulType;
	}
	
	@Override
	public void setGhoulType(@Nonnull GhoulType ghoulType) {
		this.ghoulType=ghoulType;
	}
	
	@Override
	public boolean ukaku() {
		return this.isGhoul && this.ghoulType==GhoulType.UKAKU;
	}
	
	@Override
	public UkakuState ukakuState() {
		return this.ukakuState;
	}
	
	@Override
	public void setUkakuState(UkakuState state) {
		this.ukakuState = state;
	}

	
	
	
	@Override
	public int rank()
	{
		int rankmeter = isGhoul ? skillPoints/2+RCpoints : skillPoints+1000;
		int rank = Math.round(rankmeter/1000)-1;
		return rank;
	}
	@Override
	public float exactRank() {
		float rankmeter = isGhoul ? (skillPoints+RCpoints)/2F : skillPoints+1000F;
		float rank = Math.round(rankmeter/1000) - 1;
		if (rank<0.3F)
			rank=0.3F;
		return rank;
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
		return this.isKaguneActive && this.isGhoul;
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

		this.updateEntityKagune();
		entityKagune.setReleaseTicks(master.ticksExisted);
		
		if (master instanceof EntityPlayer) {
		    ((EntityPlayer) master).world.playSound(null, ((EntityPlayer) master).getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
		}
	}
	
	@Override
	public void admitKagune() {
		master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(master.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedghoul.getID()));
		
		entityKagune.setAdmit(true);
		entityKagune.setAdmitTicks(master.ticksExisted);
	}
	
	@Override
	public void updateEntityKagune() {
		entityKagune=EnumKagune.valueOf(this.getEnumId()).getEntity(master);
	}

	
	
	
	
	
	
	@Override
	public boolean canKaguneActivated(int ticks) {
		return this.isGhoul && this.kaguneActivatedTicksPre + this.kaguneActivatedTicksGap <= ticks;
	}

	@Override
	public void setKaguneActivatedTicksPre(int ticks) {
		this.kaguneActivatedTicksPre = ticks;
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
	public boolean isKakuganActive() {
		return this.isKakuganActive && this.isGhoul;
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
		return this.isGhoul && this.isSpeedModeActive;
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
