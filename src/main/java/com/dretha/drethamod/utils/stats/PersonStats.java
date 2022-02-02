package com.dretha.drethamod.utils.stats;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.entity.human.SkinHandler;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.DrethaMath;
import com.dretha.drethamod.utils.controllers.ParticlesController;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.GrowthStages;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

public class PersonStats {

    public static final PersonStats EMPTY = new PersonStats();

    private boolean isGhoul = false;
    private boolean isDove = false;
    private int RCpoints = Oshiete.random.nextInt(501)+200;
    private int skill = 0;
    private int RClevel = MaxRClevel();
    private EntityKagune entityKagune = null;

    private final int MODEL_VARIANT = 1;
    private final int TEXTURE_VARIANT = 1;
    private String kakugan = "31";
    private ItemStack kakuganStack = SkinHandler.getKakuganStack(kakugan);
    private GhoulType ghoulType = GhoulType.NONE;
    private UkakuState ukakuState = UkakuState.NONE;
    private ImpactType impactType = ImpactType.THRUST;
    private ClothesInventory inventory = new ClothesInventory();
    private int shardCountInEntity = 0;

    private boolean isKaguneActive = false;
    private boolean isSpeedModeActive = false;
    private boolean isKakuganActive = false;

    ParticlesController patriclesController = new ParticlesController(30);

    public String getTextureLocation() {
        return String.format("textures/entity/kagune/kagune%d%02d%02d%d.png", getNNGT(), this.MODEL_VARIANT, this.TEXTURE_VARIANT, getGrowthStage().id());
    }

    public int getModelVariant() {
        return this.MODEL_VARIANT;
    }

    public int getTextureVariant() {
        return this.TEXTURE_VARIANT;
    }

    private int getNNGT() {
        return ghoulType == null ? 2 : GhoulType.indexOf(ghoulType)+1;
    }



    public String getEnumId() {
        return String.format("KAGUNE%d%02d", ghoulType.id(), this.getModelVariant());
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public int getDamage() {
        int damage = DrethaMath.getNumberOfProportion(1000, 7, RCpoints);
        return damage;
    }

    private boolean isBlock = false;

    public int getRCpoints() {
        return RCpoints;
    }

    public void setRCpoints(int RCpoints) {
        this.RCpoints = RCpoints;
    }

    public int getRClevel() {
        return RClevel;
    }

    public void setRClevel(int RClevel) {
        this.RClevel = RClevel;
    }

    public GhoulType getGhoulType() {
        return ghoulType;
    }

    public void setGhoulType(GhoulType ghoulType) {
        this.ghoulType = ghoulType;
    }

    public UkakuState getUkakuState() {
        return ukakuState;
    }

    public void setUkakuState(UkakuState ukakuState) {
        this.ukakuState = ukakuState;
    }

    public boolean isKakuganActive() {
        return isKakuganActive && isGhoul;
    }

    public void setKakuganActive(boolean kakuganActive) {
        isKakuganActive = kakuganActive;
    }


    public boolean isKaguneActive() {
        return isKaguneActive && isGhoul;
    }

    public void setKaguneActive(boolean kaguneActive) {
        isKaguneActive = kaguneActive;
    }

    public boolean isSpeedModeActive() {
        return isSpeedModeActive && isGhoul;
    }

    public void setSpeedModeActive(boolean speedModeActive) {
        isSpeedModeActive = speedModeActive;
    }

    public ImpactType getImpactType() {
        return impactType;
    }

    public void changeImpactType() {
        this.impactType = this.impactType.change();
    }

    public boolean isGhoul() {
        return isGhoul;
    }

    public void setGhoul(boolean ghoul) {
        isGhoul = ghoul;
    }

    public boolean isDove() {
        return isDove;
    }

    public void setDove(boolean dove) {
        isDove = dove;
    }


    public void becomeGhoul(GhoulType ghoulType, EntityLivingBase entity) {
        setGhoul(true);
        setGhoulType(ghoulType);
        if (ghoulType==GhoulType.UKAKU)
            this.ukakuState = UkakuState.generateState();
        addRCpoints(801, entity);
        updateRClevel();
        entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
        entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
    }

    public void becomeHuman(EntityLivingBase entity) {
        setGhoul(false);
        this.setRCpoints(Oshiete.random.nextInt(501)+200);
        this.updateRClevel();
        this.setGhoulType(GhoulType.NONE);
        entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
        entity.setHealth(19);
        entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);

        entity.sendMessage(new TextComponentString("You are already human."));
    }

    public void forceSpeed(EntityLivingBase base, float angle)
    {
        float yaw = base.rotationYaw + angle;
        while (yaw>=360) yaw-=360;

        float strength = (float) DrethaMath.getNumberOfInterval(2000D, 8000D,  ukaku() ? 4.4D : 2.2D, ukaku() ? 6.6D : 4.4D, (double)materialRank()*1000D);

        strength /= (base.isAirBorne?2F:1F);

        double xRatio = Math.cos(yaw * 0.017453292F);
        double zRatio = Math.sin(yaw * 0.017453292F);
        float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
        base.motionX /= 2.0D;
        base.motionZ /= 2.0D;
        base.motionX -= xRatio / (double)f * (double)strength;
        base.motionZ -= zRatio / (double)f * (double)strength;
    }

    public void updateRClevel() {
        this.RClevel = MaxRClevel() - (MaxRClevel() - this.RClevel);
    }

    public void removeRCpoints(int points) {
        this.RCpoints -= points;

        if (this.RCpoints < 0) this.RCpoints = 0;

    }

    public void addRCpoints(int points, EntityLivingBase base) {
        this.RCpoints += points;
        base.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40 + Math.round(exactRank()*10/1.5F));
    }




    public void removeRClevel(int points) {
        updateRClevel();
        this.RClevel -= points;
        if (this.RClevel<0) this.RClevel=0;
    }

    public void addRClevel(int points) {
        updateRClevel();
        this.RClevel += points;
        if (this.RClevel>MaxRClevel()) this.RClevel=MaxRClevel();
    }

    public boolean RClevelFull() {
        return this.RClevel == MaxRClevel();
    }

    public int MaxRClevel() {
        return RCpoints/10;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void addSkill(int points, EntityLivingBase base) {
        this.skill +=points;
        base.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(isGhoul?40:20 + Math.round((exactRank() * 10 / 1.5F) - (isGhoul?0F:4F)));
    }

    public void removeSkill(int points) {
        this.skill -=points;
        if (this.skill < 0) this.skill = 0;
    }

    public boolean ukaku() {
        return this.isGhoul() && ghoulType == GhoulType.UKAKU;
    }

    public int rank()
    {
        int rankmeter = isGhoul() ? skill/2+RCpoints : skill+1000;
        int rank = Math.round(rankmeter/1000)-1;
        return rank;
    }

    public float exactRank() {
        float rankmeter = isGhoul() ? (skill+RCpoints)/2F : skill+1000F;
        float rank = Math.round(rankmeter/1000) - 1;
        if (rank<0.3F)
            rank=0.3F;
        return rank;
    }

    public int materialRank() {
        if (isGhoul)
            return Math.max(Math.round(RCpoints/1000F)-1, 0);
        else
            return Math.max(Math.round(skill/1000F)-1, 0);
    }

    AttributeModifier speedghoul = new AttributeModifier("speedghoul", 0.5, 2);
    public void releaseKagune(EntityLivingBase base) {
        patriclesController.activate(base.ticksExisted);
        if (!base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speedghoul))
            base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedghoul);

        setKaguneActive(true);
        setKakuganActive(true);

        this.updateEntityKagune(base);
        entityKagune.getReleaseController().setTicksPre(base.ticksExisted);

        base.world.playSound(null, base.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
    }

    public void admitKagune(EntityLivingBase base) {
        if (base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speedghoul))
            base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedghoul.getID()));

        entityKagune.getAdmitController().setTicksPre(base.ticksExisted);
    }

    AttributeModifier speedmode = new AttributeModifier("speedmode", 1.6, 2);
    public void updateSpeedAttribute(EntityLivingBase base) {
        IAttributeInstance ins = base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        ins.removeAllModifiers();
        if (isKaguneActive) ins.applyModifier(speedghoul);
        if (isSpeedModeActive) ins.applyModifier(speedmode);
    }

    public void applyAtrSpeedMode(EntityLivingBase base) {
        base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedmode);
    }

    public void removeAtrSpeedMode(EntityLivingBase base) {
        base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedmode);
    }

    public @Nullable EntityKagune getKagune() {
        return entityKagune;
    }

    public ParticlesController getPatriclesController() {
        return patriclesController;
    }

    public void updateEntityKagune(EntityLivingBase base) {
        entityKagune= EnumKagune.valueOf(this.getEnumId()).getEntity(base);
    }

    public void nullKagune() {
        entityKagune = null;
    }

    public GrowthStages getGrowthStage()
    {
        GrowthStages stage = GrowthStages.NONE;

        if (isGhoul()) {
            stage = GrowthStages.getStage(RCpoints);
        }

        return stage;
    }

    public ClothesInventory getInventory(){
        return this.inventory;
    }

    public void setInventory(ClothesInventory inventory){
        this.inventory = inventory;
    }

    public void copyInventory(PersonStats stats) {
        this.inventory.copy(stats.getInventory());
    }


    public int getShardCountInEntity() {
        return this.shardCountInEntity;
    }

    public void addShardCountInEntity() {
        this.shardCountInEntity++;
    }

    public void setShardCountInEntity(int count) {
        this.shardCountInEntity = count;
    }

    public static PersonStats getStats(EntityLivingBase base) {
        if (base instanceof EntityPlayer) {
            return EventsHandler.getCapaMP((EntityPlayer) base).personStats();
        } else if (base instanceof EntityHuman && EventsHandler.hasHumanMP((EntityHuman) base)) {
                return EventsHandler.getHumanMP((EntityHuman) base).personStats();
        } else if (base instanceof EntityCorpse) {
            return ((EntityCorpse) base).personStats();
        } else return EMPTY;
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        this.getInventory().writeToNBT(compound);
        compound.setBoolean("isGhoul", this.isGhoul());
        compound.setString("ghoulType", this.getGhoulType().toString());
        compound.setString("ukakuState", this.getUkakuState().toString());
        compound.setInteger("RCpoints", this.getRCpoints());
        compound.setInteger("RClevel", this.getRClevel());
        compound.setInteger("skillPoints", this.getSkill());
        compound.setBoolean("isKaguneActive", this.isKaguneActive());
        compound.setBoolean("isKakuganActive", this.isKakuganActive());
        compound.setBoolean("isSpeedModeActive", isSpeedModeActive);
        compound.setInteger("shardCountInEntity", this.getShardCountInEntity());
        compound.setString("impactType", this.getImpactType().toString());
        compound.setString("kakugan", kakugan);
        compound.setBoolean("isDove", isDove);
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        this.getInventory().readFromNBT(compound);
        this.setGhoul(compound.getBoolean("isGhoul"));
        this.setGhoulType(GhoulType.valueOf(compound.getString("ghoulType")));
        this.setUkakuState(UkakuState.valueOf(compound.getString("ukakuState")));
        this.setRCpoints(compound.getInteger("RCpoints"));
        this.setRClevel(compound.getInteger("RClevel"));
        this.setSkill(compound.getInteger("skillPoints"));
        this.setKaguneActive(compound.getBoolean("isKaguneActive"));
        this.setKakuganActive(compound.getBoolean("isKakuganActive"));
        this.setSpeedModeActive(compound.getBoolean("isSpeedModeActive"));
        this.setShardCountInEntity(compound.getInteger("shardCountInEntity"));
        if (ImpactType.valueOf(compound.getString("impactType"))!=ImpactType.THRUST) this.changeImpactType();
        this.kakugan = compound.getString("kakugan");
        this.kakuganStack = SkinHandler.getKakuganStack(kakugan);
        this.isDove = compound.getBoolean("isDove");
    }

    public ItemStack getKakugan() {
        return kakuganStack;
    }

    public void setKakugan(String key) {
        kakugan = key;
        kakuganStack = SkinHandler.getKakuganStack(key);
    }

    /**
     * ”€звим ли гуль дл€ обычного оружи€
     */
    public boolean isVulnerable() {
        return !isGhoul || isGhoul && RClevel < MaxRClevel()/10;
    }

    public float getJumpHeight() {
        float d = isGhoul()?10F:20F;
        float height = materialRank()/d;
        height = isGhoul() ? Math.max(height, 0.1F) : height;
        return height;
    }

    public int getProtection() {
        return (int) (exactRank() * 10 * ghoulType.blockMultiplier);
    }

}
