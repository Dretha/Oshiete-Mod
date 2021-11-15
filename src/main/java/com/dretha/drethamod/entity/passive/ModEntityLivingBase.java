package com.dretha.drethamod.entity.passive;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.CombatRules;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ModEntityLivingBase extends EntityCreature
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final AttributeModifier SPRINTING_SPEED_BOOST = (new AttributeModifier(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", 0.30000001192092896D, 2)).setSaved(false);
    /**
     * Hand states, used to trigger blocking/eating/drinking animation.
     *  
     * Note that this is completely unrelated to {@link #isSwingInProgress}/{@link #swingingHand}, which is used for the
     * swinging animation.
     */
    protected static final DataParameter<Byte> HAND_STATES = EntityDataManager.<Byte>createKey(EntityLivingBase.class, DataSerializers.BYTE);
    private static final DataParameter<Float> HEALTH = EntityDataManager.<Float>createKey(EntityLivingBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> POTION_EFFECTS = EntityDataManager.<Integer>createKey(EntityLivingBase.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HIDE_PARTICLES = EntityDataManager.<Boolean>createKey(EntityLivingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> ARROW_COUNT_IN_ENTITY = EntityDataManager.<Integer>createKey(EntityLivingBase.class, DataSerializers.VARINT);
    private AbstractAttributeMap attributeMap;
    private final CombatTracker _combatTracker = new CombatTracker(this);
    private final Map<Potion, PotionEffect> activePotionsMap = Maps.<Potion, PotionEffect>newHashMap();
    private final NonNullList<ItemStack> handInventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    /** The array of item stacks that are used for armor in a living inventory. */
    private final NonNullList<ItemStack> armorArray = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    /** Whether an arm swing is currently in progress. */
    public boolean isSwingInProgress;
    /** The hand that is currently being swung, if {@link #isSwingInProgress} is true. */
    public EnumHand swingingHand;
    public int swingProgressInt;
    public int arrowHitTimer;
    /** The amount of time remaining this entity should act 'hurt'. (Visual appearance of red tint) */
    public int hurtTime;
    /** What the hurt time was max set to last. */
    public int maxHurtTime;
    /** The yaw at which this entity was last attacked from. */
    public float attackedAtYaw;
    /** The amount of time remaining this entity should act 'dead', i.e. have a corpse in the world. */
    public int deathTime;
    public float prevSwingProgress;
    public float swingProgress;
    protected int ticksSinceLastSwing;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public int maxHurtResistantTime = 0;
    public float prevCameraPitch;
    public float cameraPitch;
    /** An unused random value set in the constructor to a random number between 0 and 12398 */
    public float randomUnused2;
    /** An unused random value set in the constructor to a random number between .01 and .02 */
    public float randomUnused1;
    public float renderYawOffset;
    public float prevRenderYawOffset;
    /** Entity head rotation yaw */
    public float rotationYawHead;
    /** Entity head rotation yaw at previous tick */
    public float prevRotationYawHead;
    /** A factor used to determine how far this entity will move each tick if it is jumping or falling. */
    public float jumpMovementFactor = 0.02F;
    /** The most recent player that has attacked this entity */
    protected EntityPlayer attackingPlayer;
    /**
     * Set to 60 when hit by the player or the player's wolf, then decrements. Used to determine whether the entity
     * should drop items on death.
     */
    protected int recentlyHit;
    /** This gets set on entity death, but never used. Looks like a duplicate of isDead */
    protected boolean dead;
    /** The age of this EntityLiving (used to determine when it dies) */
    protected int idleTime;
    protected float prevOnGroundSpeedFactor;
    protected float onGroundSpeedFactor;
    protected float movedDistance;
    protected float prevMovedDistance;
    /** An unused field that is set to 180 in the constructor of EntityPlayer (and otherwise is 0) */
    protected float unused180;
    /** The score value of the Mob, the amount of points the mob is worth. */
    protected int scoreValue;
    /** Damage taken in the last hit. Mobs are resistant to damage less than this for a short time after taking damage. */
    protected float lastDamage;
    /** used to check whether entity is jumping. */
    protected boolean isJumping;
    public float moveStrafing;
    public float moveVertical;
    public float moveForward;
    public float randomYawVelocity;
    /** The number of updates over which the new position and rotation are to be applied to the entity. */
    protected int newPosRotationIncrements;
    /** The X position the entity will be interpolated to. Used for teleporting. */
    protected double interpTargetX;
    /** The Y position the entity will be interpolated to. Used for teleporting. */
    protected double interpTargetY;
    /** The Z position the entity will be interpolated to. Used for teleporting. */
    protected double interpTargetZ;
    /** The yaw rotation the entity will be interpolated to. Used for teleporting. */
    protected double interpTargetYaw;
    /** The pitch rotation the entity will be interpolated to. Used for teleporting. */
    protected double interpTargetPitch;
    /** Whether the DataWatcher needs to be updated with the active potions */
    private boolean potionsNeedUpdate = true;
    /**
     * Set immediately after this entity is attacked by another EntityLivingBase, allowing AI tasks to see who the
     * attacker was and handle accordingly. Reset to null after 100 ticks have passed.
     */
    private EntityLivingBase revengeTarget;
    private int revengeTimer;
    private EntityLivingBase lastAttackedEntity;
    /** Holds the value of ticksExisted when setLastAttacker was last called. */
    private int lastAttackedEntityTime;
    /**
     * A factor used to determine how far this entity will move each tick if it is walking on land. Adjusted by speed,
     * and slipperiness of the current block.
     */
    private float landMovementFactor;
    /** Number of ticks since last jump */
    private int jumpTicks;
    private float absorptionAmount;
    protected ItemStack activeItemStack = ItemStack.EMPTY;
    protected int activeItemStackUseCount;
    protected int ticksElytraFlying;
    /** The BlockPos the entity had during the previous tick. */
    private BlockPos prevBlockpos;
    private DamageSource lastDamageSource;
    private long lastDamageStamp;


    public ModEntityLivingBase(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.preventEntitySpawning = true;
        this.randomUnused1 = (float)((Math.random() + 1.0D) * 0.009999999776482582D);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.randomUnused2 = (float)Math.random() * 12398.0F;
        this.rotationYaw = (float)(Math.random() * (Math.PI * 2D));
        this.rotationYawHead = this.rotationYaw;
        this.stepHeight = 0.6F;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount)) return false;
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (this.world.isRemote)
        {
            return false;
        }
        else
        {
            this.idleTime = 0;

            if (this.getHealth() <= 0.0F)
            {
                return false;
            }
            else if (source.isFireDamage() && this.isPotionActive(MobEffects.FIRE_RESISTANCE))
            {
                return false;
            }
            else
            {
                float f = amount;

                if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
                {
                    this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int)(amount * 4.0F + this.rand.nextFloat() * amount * 2.0F), this);
                    amount *= 0.75F;
                }

                boolean flag = false;

                if (amount > 0.0F && this.canBlockDamageSource(source))
                {
                    this.damageShield(amount);
                    amount = 0.0F;

                    if (!source.isProjectile())
                    {
                        Entity entity = source.getImmediateSource();

                        if (entity instanceof EntityLivingBase)
                        {
                            this.blockUsingShield((EntityLivingBase)entity);
                        }
                    }

                    flag = true;
                }

                this.limbSwingAmount = 1.5F;
                boolean flag1 = true;

                if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F)
                {
                    if (amount <= this.lastDamage)
                    {
                        return false;
                    }

                    this.damageEntity(source, amount - this.lastDamage);
                    this.lastDamage = amount;
                    flag1 = false;
                }
                else
                {
                    this.lastDamage = amount;
                    this.hurtResistantTime = this.maxHurtResistantTime;
                    this.damageEntity(source, amount);
                    this.maxHurtTime = 0;
                    this.hurtTime = this.maxHurtTime;
                }

                this.attackedAtYaw = 0.0F;
                Entity entity1 = source.getTrueSource();

                if (entity1 != null)
                {
                    if (entity1 instanceof EntityLivingBase)
                    {
                        this.setRevengeTarget((EntityLivingBase)entity1);
                    }

                    if (entity1 instanceof EntityPlayer)
                    {
                        this.recentlyHit = 100;
                        this.attackingPlayer = (EntityPlayer)entity1;
                    }
                    else if (entity1 instanceof net.minecraft.entity.passive.EntityTameable)
                    {
                        net.minecraft.entity.passive.EntityTameable entitywolf = (net.minecraft.entity.passive.EntityTameable)entity1;

                        if (entitywolf.isTamed())
                        {
                            this.recentlyHit = 100;
                            this.attackingPlayer = null;
                        }
                    }
                }

                if (flag1)
                {
                    if (flag)
                    {
                        this.world.setEntityState(this, (byte)29);
                    }
                    else if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
                    {
                        this.world.setEntityState(this, (byte)33);
                    }
                    else
                    {
                        byte b0;

                        if (source == DamageSource.DROWN)
                        {
                            b0 = 36;
                        }
                        else if (source.isFireDamage())
                        {
                            b0 = 37;
                        }
                        else
                        {
                            b0 = 2;
                        }

                        this.world.setEntityState(this, b0);
                    }

                    if (source != DamageSource.DROWN && (!flag || amount > 0.0F))
                    {
                        this.markVelocityChanged();
                    }

                    if (entity1 != null)
                    {
                        double d1 = entity1.posX - this.posX;
                        double d0;

                        for (d0 = entity1.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * (180D / Math.PI) - (double)this.rotationYaw);
                        this.knockBack(entity1, 0.4F, d1, d0);
                    }
                    else
                    {
                        this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                if (this.getHealth() <= 0.0F)
                {
                    if (!this.checkTotemDeathProtection(source))
                    {
                        SoundEvent soundevent = this.getDeathSound();

                        if (flag1 && soundevent != null)
                        {
                            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
                        }

                        this.onDeath(source);
                    }
                }
                else if (flag1)
                {
                    this.playHurtSound(source);
                }

                boolean flag2 = !flag || amount > 0.0F;

                if (flag2)
                {
                    this.lastDamageSource = source;
                    this.lastDamageStamp = this.world.getTotalWorldTime();
                }

                

                if (entity1 instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP)entity1, this, source, f, amount, flag);
                }

                return flag2;
            }
        }
    }

    private boolean checkTotemDeathProtection(DamageSource p_190628_1_)
    {
        if (p_190628_1_.canHarmInCreative())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = null;

            for (EnumHand enumhand : EnumHand.values())
            {
                ItemStack itemstack1 = this.getHeldItem(enumhand);

                if (itemstack1.getItem() == Items.TOTEM_OF_UNDYING)
                {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null)
            {
                

                this.setHealth(1.0F);
                this.clearActivePotions();
                this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 900, 1));
                this.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 100, 1));
                this.world.setEntityState(this, (byte)35);
            }

            return itemstack != null;
        }
    }

    /**
     * Determines whether the entity can block the damage source based on the damage source's location, whether the
     * damage source is blockable, and whether the entity is blocking.
     */
    private boolean canBlockDamageSource(DamageSource damageSourceIn)
    {
        if (!damageSourceIn.isUnblockable() && this.isActiveItemStackBlocking())
        {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null)
            {
                Vec3d vec3d1 = this.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(this.posX, this.posY, this.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D)
                {
                    return true;
                }
            }
        }

        return false;
    }


    

    

    
    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void handleStatusUpdate(byte id)
    {
        boolean flag = id == 33;
        boolean flag1 = id == 36;
        boolean flag2 = id == 37;

        if (id != 2 && !flag && !flag1 && !flag2)
        {
            if (id == 3)
            {
                SoundEvent soundevent1 = this.getDeathSound();

                if (soundevent1 != null)
                {
                    this.playSound(soundevent1, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }

                this.setHealth(0.0F);
                this.onDeath(DamageSource.GENERIC);
            }
            else if (id == 30)
            {
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
            else if (id == 29)
            {
                this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
            else
            {
                super.handleStatusUpdate(id);
            }
        }
        else
        {
            this.limbSwingAmount = 1.5F;
            this.hurtResistantTime = this.maxHurtResistantTime;
            this.maxHurtTime = 0;
            this.hurtTime = this.maxHurtTime;
            this.attackedAtYaw = 0.0F;

            if (flag)
            {
                this.playSound(SoundEvents.ENCHANT_THORNS_HIT, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            DamageSource damagesource;

            if (flag2)
            {
                damagesource = DamageSource.ON_FIRE;
            }
            else if (flag1)
            {
                damagesource = DamageSource.DROWN;
            }
            else
            {
                damagesource = DamageSource.GENERIC;
            }

            SoundEvent soundevent = this.getHurtSound(damagesource);

            if (soundevent != null)
            {
                this.playSound(soundevent, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
        }
    }
}
