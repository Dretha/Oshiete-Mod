package com.dretha.drethamod.utils.stats;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.client.geckolib.kagunes.EntityFlame;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneHolder;
import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.configurations.Configuration;
import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.entity.human.SkinHandler;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.DrethaMath;
import com.dretha.drethamod.utils.controllers.ParticlesController;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.GrowthStages;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import javax.annotation.Nullable;
import java.awt.*;

public class PersonStats {

    public static final PersonStats EMPTY = new PersonStats();

    private boolean isGhoul = false;
    private boolean isDove = false;
    private int RCpoints = Oshiete.random.nextInt(501)+200;
    private int skill = 0;
    private int RClevel = maxRClevel();
    public static final int MAX_RC_FOR_STATS = 14000;
    private EntityKagune entityKagune = null;

    private final int MODEL_VARIANT = 1;
    private int red = 255;
    private int green = 255;
    private int blue = 255;
    private ResourceLocation kakugan = SkinHandler.kakugan31;
    private GhoulType ghoulType = GhoulType.NONE;
    private UkakuState ukakuState = UkakuState.NONE;
    private ImpactType impactType = ImpactType.THRUST;
    private ClothesInventory inventory = new ClothesInventory();
    private int shardCountInEntity = 0;

    private boolean isKaguneActive = false;
    private boolean isSpeedModeActive = false;
    private boolean isKakuganActive = false;

    ParticlesController patriclesController = new ParticlesController(30);

    public String getFirstTextureLocation() {
        if (ukaku())
        {
            int frame = ((EntityFlame)entityKagune).getTextureFrame();
            return String.format("textures/entity/kagune/kagune%d%02d%d.png", ghoulType.id(), this.MODEL_VARIANT, frame);
        }
        return String.format("textures/entity/kagune/kagune%d%02d.png", ghoulType.id(), this.MODEL_VARIANT);
    }
    public String getSecondTextureLocation() {
        if (ghoulType==GhoulType.RINKAKU)
            return String.format("textures/entity/kagune/second_layer/kagune%d%02d%d.png", ghoulType.id(), this.MODEL_VARIANT, getGrowthStage().id());
        return String.format("textures/entity/kagune/second_layer/kagune%d%02d.png", ghoulType.id(), this.MODEL_VARIANT);
    }

    public int getModelVariant() {
        return this.MODEL_VARIANT;
    }

    public String getKaguneHolderName() {
        if (ghoulType==GhoulType.RINKAKU)
            return String.format("kagune%d%02d%d", ghoulType.id(), this.getModelVariant(), getGrowthStage().id());
        return String.format("kagune%d%02d", ghoulType.id(), this.getModelVariant());
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public int getDamage() {
        return getCombatPoint(RCpoints);
    }

    public static int getCombatPoint(int points)
    {
        return (int) DrethaMath.getNumberOfInterval(1000, 3000, 5000, MAX_RC_FOR_STATS, 6, 12, 20, 40, points);
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
        becomeGhoul(ghoulType, entity, 801, 0);
    }
    public void becomeGhoul(GhoulType ghoulType, EntityLivingBase base, int rc, int skill) {
        rc = Math.max(rc, 801);
        setGhoul(true);
        setGhoulType(ghoulType);
        if (ghoulType==GhoulType.UKAKU)
            ukakuState = UkakuState.FLAMELIMB;
        RCpoints = Oshiete.random.nextInt(501)+200;
        addRCpoints(rc, base);
        addSkill(skill, base);
        RClevel = maxRClevel();
        base.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
        base.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        base.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
        base.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
    }

    public void becomeHuman(EntityLivingBase entity) {
        setGhoul(false);
        RCpoints = Oshiete.random.nextInt(501)+200;
        this.updateRClevel();
        this.setGhoulType(GhoulType.NONE);
        entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
        entity.setHealth(19);
        entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0);
        entity.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);

        entity.sendMessage(new TextComponentString("You are already human."));
    }

    public void becomeDove(EntityLivingBase entity) {
        becomeDove(entity, 0);
    }
    public void becomeDove(EntityLivingBase base, int skill) {
        setDove(true);
        addSkill(1000+skill, base);
        updateCharacteristics(base);
    }

    public void forceSpeed(EntityLivingBase base, float angle)
    {
        float yaw = base.rotationYaw + angle;
        while (yaw>=360) yaw-=360;

        float strength = (float) DrethaMath.getNumberOfInterval(2000D, 8000D,  ukaku() ? 4.4D : 2.2D, ukaku() ? 6.6D : 4.4D, (double)materialRank()*1000D);

        if (base.isAirBorne)
            strength /= 4F;

        double xRatio = Math.cos(yaw * 0.017453292F);
        double zRatio = Math.sin(yaw * 0.017453292F);
        float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
        base.motionX /= 2.0D;
        base.motionZ /= 2.0D;
        base.motionX -= xRatio / (double)f * (double)strength;
        base.motionZ -= zRatio / (double)f * (double)strength;
    }

    public void updateRClevel() {
        this.RClevel = maxRClevel() - (maxRClevel() - this.RClevel);
        if (RCpoints<1000)
            RClevel=0;
    }

    public void addRCpoints(int points, EntityLivingBase base)
    {
        if (RCpoints + points >= 1000)
        {
            boolean isDarking = true;
            if (base instanceof EntityPlayer)
                isDarking = base.getCapability(CapaProvider.PLAYER_CAP, null).isDarkeningKagune();
            GrowthStages stageOld = getGrowthStage();
            this.RCpoints += points;
            GrowthStages stageNew = getGrowthStage();
            if (stageNew != stageOld && isDarking) {
                red = red - (red / 4 * (stageNew.ordinal() - stageOld.ordinal()));
                green = green - (green / 4 * (stageNew.ordinal() - stageOld.ordinal()));
                blue = blue - (blue / 4 * (stageNew.ordinal() - stageOld.ordinal()));

                red = normalizeColor(red);
                green = normalizeColor(green);
                blue = normalizeColor(blue);
            }
            updateCharacteristics(base);
            updateEntityKagune(base);
        }
    }

    public void updateCharacteristics(EntityLivingBase base) {
        base.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(isGhoul?40:20 + Math.round((exactRank() * 10 / 1.5F) - (isGhoul?0F:4F)));
        base.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((isGhoul?4:1) + rank() + (ukaku()?2:0));
    }



    public void removeRClevel(int points) {
        if (points<0) return;
        updateRClevel();
        this.RClevel -= points;
        if (this.RClevel<0) this.RClevel=0;
    }

    public void addRClevel(int points) {
        updateRClevel();
        this.RClevel += points;
        if (this.RClevel> maxRClevel()) this.RClevel= maxRClevel();
    }

    public boolean RClevelFull() {
        return this.RClevel == maxRClevel();
    }

    public int maxRClevel() {
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
        if (this.skill < 0) this.skill = 0;
        updateCharacteristics(base);
    }

    public void removeSkill(int points) {
        this.skill -=points;
        if (this.skill < 0) this.skill = 0;
    }

    public boolean ukaku() {
        return this.isGhoul() && ghoulType == GhoulType.UKAKU;
    }

    public boolean rinkaku() {
        return this.isGhoul() && ghoulType == GhoulType.RINKAKU;
    }


    public int rank()
    {
        int rankmeter = isGhoul() ? skill/2+RCpoints : skill+1000;
        int rank = Math.round(rankmeter/1000)-1;
        return rank;
    }

    public float exactRank() {
        float rankmeter = isGhoul() ? (skill+RCpoints)/2F : skill;
        float rank = Math.round(rankmeter/1000) - 1;
        rank = Math.max(0.3F, rank);
        return rank;
    }

    public int materialRank() {
        if (isGhoul)
            return Math.max(Math.round(RCpoints/1000F)-1, 0);
        else
            return Math.max(Math.round(skill/1000F)-1, 0);
    }
    public double exactMaterialRank() {
        if (isGhoul)
            return Math.max(Math.round(RCpoints/1000F)-1, 0);
        else
            return Math.max(Math.round(skill/1000F)-1, 0);
    }

    AttributeModifier speedghoul = new AttributeModifier("speedghoul", Configuration.GHOUL_SPEED_BOOST, 2);
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
        if (entityKagune==null) return;
        if (base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speedghoul))
            base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(base.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedghoul.getID()));

        entityKagune.getAdmitController().setTicksPre(base.ticksExisted);
    }

    AttributeModifier speedmode = new AttributeModifier("speedmode", Configuration.GHOUL_SPEED_MODE_BOOST, 2);
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
        entityKagune= KaguneHolder.valueOf(this.getKaguneHolderName()).getEntity(base);
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

    public void removeShardCountInEntity() {
        shardCountInEntity--;
        if (shardCountInEntity<0)
            shardCountInEntity=0;
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
        compound.setString("kakugan", kakugan.getResourcePath());
        compound.setBoolean("isDove", isDove);
        compound.setInteger("red", red);
        compound.setInteger("green", green);
        compound.setInteger("blue", blue);
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
        this.kakugan = new ResourceLocation(Reference.MODID, compound.getString("kakugan"));
        this.isDove = compound.getBoolean("isDove");
        red = compound.getInteger("red");
        green = compound.getInteger("green");
        blue = compound.getInteger("blue");
    }

    public void setKakuganResource(ResourceLocation location) {
        kakugan = location;
    }

    public ResourceLocation getKakuganResource() {
        return kakugan;
    }

    /**
     * ”€звим ли гуль дл€ обычного оружи€
     */
    public boolean isVulnerable() {
        return !isGhoul || isGhoul && RClevel < maxRClevel()/10;
    }

    public float getJumpHeight() {
        float d = isGhoul()?10F:20F;
        float height = materialRank()/d;
        height = isGhoul() ? Math.max(height, 0.1F) : height;
        height *= ukaku() ? 2 : 1;
        return height;
    }

    // TODO защита маленька€ пофиксь
    public int getProtection() {
        return getCombatPoint((int) (skill * ghoulType.blockMultiplier));
    }

    //for human
    public boolean isCombatReady(EntityLiving living) {
        return isNotCivillian() && living.getHealth() >= living.getMaxHealth()/5;
    }
    public boolean hostileTo(EntityLiving master, EntityLivingBase base) {
        PersonStats statsBase = getStats(base);
        boolean targetIsMonster = base.isCreatureType(EnumCreatureType.MONSTER, false) && !(base instanceof EntityHuman);
        return isCombatReady(master) && ((this.isGhoul && !statsBase.isGhoul) || (this.isDove && statsBase.isGhoul) || (this.isDove && targetIsMonster)) ;
    }

    public boolean isMyTeammate(EntityLiving living) {
        PersonStats stats = getStats(living);
        return ((stats.isGhoul==isGhoul&&isGhoul) || stats.isDove) && (stats!=PersonStats.EMPTY || (living instanceof EntityIronGolem && !isGhoul));
    }

    public boolean isNotCivillian() {
        return isGhoul || isDove;
    }

    public boolean isMaskOff(EntityLivingBase base)
    {
        ItemStack helmet = base.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        return getInventory().getStackInSlot(0).isEmpty() && (helmet.getItem() instanceof ItemArmor || helmet.isEmpty());
    }

    public boolean iAmStrongerThanHim(EntityLivingBase base) {
        PersonStats stats = PersonStats.getStats(base);
        return GhoulType.getWeakType(ghoulType)==stats.getGhoulType();
    }


    public int getRed() {
        return normalizeColor(red);
    }

    public void setRed(int color) {
        this.red = normalizeColor(color);
    }

    public int getGreen() {
        return normalizeColor(green);
    }

    public void setGreen(int color) {
        this.green = normalizeColor(color);
    }

    public int getBlue() {
        return normalizeColor(blue);
    }

    public void setBlue(int color) {
        this.blue = normalizeColor(color);
    }

    private int normalizeColor(int color) {
        color = Math.max(0, color);
        color = Math.min(255, color);
        return color;
    }

    public int getDecimalColor() {
        Color color = new Color(red, green, blue);
        return color.getRGB();
    }
}
