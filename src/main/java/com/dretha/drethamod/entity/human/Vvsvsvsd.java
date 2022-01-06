package com.dretha.drethamod.entity.human;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.ITeleporter;

import java.util.*;

public class Vvsvsvsd extends EntityMob{

	@Override
	public SoundCategory getSoundCategory() {
		// TODO Автоматически созданная заглушка метода
		return super.getSoundCategory();
	}

	@Override
	public void onLivingUpdate() {
		// TODO Автоматически созданная заглушка метода
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		// TODO Автоматически созданная заглушка метода
		super.onUpdate();
	}

	@Override
	protected SoundEvent getSwimSound() {
		// TODO Автоматически созданная заглушка метода
		return super.getSwimSound();
	}

	@Override
	protected SoundEvent getSplashSound() {
		// TODO Автоматически созданная заглушка метода
		return super.getSplashSound();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO Автоматически созданная заглушка метода
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Автоматически созданная заглушка метода
		return super.getDeathSound();
	}

	@Override
	protected SoundEvent getFallSound(int heightIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getFallSound(heightIn);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		return super.getBlockPathWeight(pos);
	}

	@Override
	protected boolean isValidLightLevel() {
		// TODO Автоматически созданная заглушка метода
		return super.isValidLightLevel();
	}

	@Override
	public boolean getCanSpawnHere() {
		// TODO Автоматически созданная заглушка метода
		return super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		// TODO Автоматически созданная заглушка метода
		super.applyEntityAttributes();
	}

	@Override
	protected boolean canDropLoot() {
		// TODO Автоматически созданная заглушка метода
		return super.canDropLoot();
	}

	@Override
	public boolean isPreventingPlayerRest(EntityPlayer playerIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isPreventingPlayerRest(playerIn);
	}

	@Override
	public boolean hasPath() {
		// TODO Автоматически созданная заглушка метода
		return super.hasPath();
	}

	@Override
	public boolean isWithinHomeDistanceCurrentPosition() {
		// TODO Автоматически созданная заглушка метода
		return super.isWithinHomeDistanceCurrentPosition();
	}

	@Override
	public boolean isWithinHomeDistanceFromPosition(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		return super.isWithinHomeDistanceFromPosition(pos);
	}

	@Override
	public void setHomePosAndDistance(BlockPos pos, int distance) {
		// TODO Автоматически созданная заглушка метода
		super.setHomePosAndDistance(pos, distance);
	}

	@Override
	public BlockPos getHomePosition() {
		// TODO Автоматически созданная заглушка метода
		return super.getHomePosition();
	}

	@Override
	public float getMaximumHomeDistance() {
		// TODO Автоматически созданная заглушка метода
		return super.getMaximumHomeDistance();
	}

	@Override
	public void detachHome() {
		// TODO Автоматически созданная заглушка метода
		super.detachHome();
	}

	@Override
	public boolean hasHome() {
		// TODO Автоматически созданная заглушка метода
		return super.hasHome();
	}

	@Override
	protected void updateLeashedState() {
		// TODO Автоматически созданная заглушка метода
		super.updateLeashedState();
	}

	@Override
	protected double followLeashSpeed() {
		// TODO Автоматически созданная заглушка метода
		return super.followLeashSpeed();
	}

	@Override
	protected void onLeashDistance(float p_142017_1_) {
		// TODO Автоматически созданная заглушка метода
		super.onLeashDistance(p_142017_1_);
	}

	@Override
	protected void initEntityAI() {
		// TODO Автоматически созданная заглушка метода
		super.initEntityAI();
	}

	@Override
	protected PathNavigate createNavigator(World worldIn) {
		// TODO Автоматически созданная заглушка метода
		return super.createNavigator(worldIn);
	}

	@Override
	public float getPathPriority(PathNodeType nodeType) {
		// TODO Автоматически созданная заглушка метода
		return super.getPathPriority(nodeType);
	}

	@Override
	public void setPathPriority(PathNodeType nodeType, float priority) {
		// TODO Автоматически созданная заглушка метода
		super.setPathPriority(nodeType, priority);
	}

	@Override
	protected EntityBodyHelper createBodyHelper() {
		// TODO Автоматически созданная заглушка метода
		return super.createBodyHelper();
	}

	@Override
	public EntityLookHelper getLookHelper() {
		// TODO Автоматически созданная заглушка метода
		return super.getLookHelper();
	}

	@Override
	public EntityMoveHelper getMoveHelper() {
		// TODO Автоматически созданная заглушка метода
		return super.getMoveHelper();
	}

	@Override
	public EntityJumpHelper getJumpHelper() {
		// TODO Автоматически созданная заглушка метода
		return super.getJumpHelper();
	}

	@Override
	public PathNavigate getNavigator() {
		// TODO Автоматически созданная заглушка метода
		return super.getNavigator();
	}

	@Override
	public EntitySenses getEntitySenses() {
		// TODO Автоматически созданная заглушка метода
		return super.getEntitySenses();
	}

	@Override
	public EntityLivingBase getAttackTarget() {
		// TODO Автоматически созданная заглушка метода
		return super.getAttackTarget();
	}

	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		// TODO Автоматически созданная заглушка метода
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
		// TODO Автоматически созданная заглушка метода
		return super.canAttackClass(cls);
	}

	@Override
	public void eatGrassBonus() {
		// TODO Автоматически созданная заглушка метода
		super.eatGrassBonus();
	}

	@Override
	protected void entityInit() {
		// TODO Автоматически созданная заглушка метода
		super.entityInit();
	}

	@Override
	public int getTalkInterval() {
		// TODO Автоматически созданная заглушка метода
		return super.getTalkInterval();
	}

	@Override
	public void playLivingSound() {
		// TODO Автоматически созданная заглушка метода
		super.playLivingSound();
	}

	@Override
	public void onEntityUpdate() {
		// TODO Автоматически созданная заглушка метода
		super.onEntityUpdate();
	}

	@Override
	protected void playHurtSound(DamageSource source) {
		// TODO Автоматически созданная заглушка метода
		super.playHurtSound(source);
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		// TODO Автоматически созданная заглушка метода
		return super.getExperiencePoints(player);
	}

	@Override
	public void spawnExplosionParticle() {
		// TODO Автоматически созданная заглушка метода
		super.spawnExplosionParticle();
	}

	@Override
	public void handleStatusUpdate(byte id) {
		// TODO Автоматически созданная заглушка метода
		super.handleStatusUpdate(id);
	}

	@Override
	protected float updateDistance(float p_110146_1_, float p_110146_2_) {
		// TODO Автоматически созданная заглушка метода
		return super.updateDistance(p_110146_1_, p_110146_2_);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		// TODO Автоматически созданная заглушка метода
		return super.getAmbientSound();
	}

	@Override
	protected Item getDropItem() {
		// TODO Автоматически созданная заглушка метода
		return super.getDropItem();
	}

	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		// TODO Автоматически созданная заглушка метода
		super.dropFewItems(wasRecentlyHit, lootingModifier);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		super.readEntityFromNBT(compound);
	}

	@Override
	protected ResourceLocation getLootTable() {
		// TODO Автоматически созданная заглушка метода
		return super.getLootTable();
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		// TODO Автоматически созданная заглушка метода
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}

	@Override
	public void setMoveForward(float amount) {
		// TODO Автоматически созданная заглушка метода
		super.setMoveForward(amount);
	}

	@Override
	public void setMoveVertical(float amount) {
		// TODO Автоматически созданная заглушка метода
		super.setMoveVertical(amount);
	}

	@Override
	public void setMoveStrafing(float amount) {
		// TODO Автоматически созданная заглушка метода
		super.setMoveStrafing(amount);
	}

	@Override
	public void setAIMoveSpeed(float speedIn) {
		// TODO Автоматически созданная заглушка метода
		super.setAIMoveSpeed(speedIn);
	}

	@Override
	protected void updateEquipmentIfNeeded(EntityItem itemEntity) {
		// TODO Автоматически созданная заглушка метода
		super.updateEquipmentIfNeeded(itemEntity);
	}

	@Override
	protected boolean canEquipItem(ItemStack stack) {
		// TODO Автоматически созданная заглушка метода
		return super.canEquipItem(stack);
	}

	@Override
	protected boolean canDespawn() {
		// TODO Автоматически созданная заглушка метода
		return super.canDespawn();
	}

	@Override
	protected void despawnEntity() {
		// TODO Автоматически созданная заглушка метода
		super.despawnEntity();
	}

	@Override
	protected void updateAITasks() {
		// TODO Автоматически созданная заглушка метода
		super.updateAITasks();
	}

	@Override
	public int getVerticalFaceSpeed() {
		// TODO Автоматически созданная заглушка метода
		return super.getVerticalFaceSpeed();
	}

	@Override
	public int getHorizontalFaceSpeed() {
		// TODO Автоматически созданная заглушка метода
		return super.getHorizontalFaceSpeed();
	}

	@Override
	public void faceEntity(Entity entityIn, float maxYawIncrease, float maxPitchIncrease) {
		// TODO Автоматически созданная заглушка метода
		super.faceEntity(entityIn, maxYawIncrease, maxPitchIncrease);
	}

	@Override
	public boolean isNotColliding() {
		// TODO Автоматически созданная заглушка метода
		return super.isNotColliding();
	}

	@Override
	public float getRenderSizeModifier() {
		// TODO Автоматически созданная заглушка метода
		return super.getRenderSizeModifier();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		// TODO Автоматически созданная заглушка метода
		return super.getMaxSpawnedInChunk();
	}

	@Override
	public int getMaxFallHeight() {
		// TODO Автоматически созданная заглушка метода
		return super.getMaxFallHeight();
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment() {
		// TODO Автоматически созданная заглушка метода
		return super.getHeldEquipment();
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		// TODO Автоматически созданная заглушка метода
		return super.getArmorInventoryList();
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getItemStackFromSlot(slotIn);
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		// TODO Автоматически созданная заглушка метода
		super.setItemStackToSlot(slotIn, stack);
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		// TODO Автоматически созданная заглушка метода
		super.dropEquipment(wasRecentlyHit, lootingModifier);
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO Автоматически созданная заглушка метода
		super.setEquipmentBasedOnDifficulty(difficulty);
	}

	@Override
	protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO Автоматически созданная заглушка метода
		super.setEnchantmentBasedOnDifficulty(difficulty);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		// TODO Автоматически созданная заглушка метода
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	public boolean canBeSteered() {
		// TODO Автоматически созданная заглушка метода
		return super.canBeSteered();
	}

	@Override
	public void enablePersistence() {
		// TODO Автоматически созданная заглушка метода
		super.enablePersistence();
	}

	@Override
	public void setDropChance(EntityEquipmentSlot slotIn, float chance) {
		// TODO Автоматически созданная заглушка метода
		super.setDropChance(slotIn, chance);
	}

	@Override
	public boolean canPickUpLoot() {
		// TODO Автоматически созданная заглушка метода
		return super.canPickUpLoot();
	}

	@Override
	public void setCanPickUpLoot(boolean canPickup) {
		// TODO Автоматически созданная заглушка метода
		super.setCanPickUpLoot(canPickup);
	}

	@Override
	public boolean isNoDespawnRequired() {
		// TODO Автоматически созданная заглушка метода
		return super.isNoDespawnRequired();
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		// TODO Автоматически созданная заглушка метода
		return super.processInteract(player, hand);
	}

	@Override
	public void clearLeashed(boolean sendPacket, boolean dropLead) {
		// TODO Автоматически созданная заглушка метода
		super.clearLeashed(sendPacket, dropLead);
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		// TODO Автоматически созданная заглушка метода
		return super.canBeLeashedTo(player);
	}

	@Override
	public boolean getLeashed() {
		// TODO Автоматически созданная заглушка метода
		return super.getLeashed();
	}

	@Override
	public Entity getLeashHolder() {
		// TODO Автоматически созданная заглушка метода
		return super.getLeashHolder();
	}

	@Override
	public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
		// TODO Автоматически созданная заглушка метода
		super.setLeashHolder(entityIn, sendAttachNotification);
	}

	@Override
	public boolean startRiding(Entity entityIn, boolean force) {
		// TODO Автоматически созданная заглушка метода
		return super.startRiding(entityIn, force);
	}

	@Override
	public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
		// TODO Автоматически созданная заглушка метода
		return super.replaceItemInInventory(inventorySlot, itemStackIn);
	}

	@Override
	public boolean canPassengerSteer() {
		// TODO Автоматически созданная заглушка метода
		return super.canPassengerSteer();
	}

	@Override
	public boolean isServerWorld() {
		// TODO Автоматически созданная заглушка метода
		return super.isServerWorld();
	}

	@Override
	public void setNoAI(boolean disable) {
		// TODO Автоматически созданная заглушка метода
		super.setNoAI(disable);
	}

	@Override
	public void setLeftHanded(boolean leftHanded) {
		// TODO Автоматически созданная заглушка метода
		super.setLeftHanded(leftHanded);
	}

	@Override
	public boolean isAIDisabled() {
		// TODO Автоматически созданная заглушка метода
		return super.isAIDisabled();
	}

	@Override
	public boolean isLeftHanded() {
		// TODO Автоматически созданная заглушка метода
		return super.isLeftHanded();
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		// TODO Автоматически созданная заглушка метода
		return super.getPrimaryHand();
	}

	@Override
	public void onKillCommand() {
		// TODO Автоматически созданная заглушка метода
		super.onKillCommand();
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		super.updateFallState(y, onGroundIn, state, pos);
	}

	@Override
	public boolean canBreatheUnderwater() {
		// TODO Автоматически созданная заглушка метода
		return super.canBreatheUnderwater();
	}

	@Override
	protected void frostWalk(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		super.frostWalk(pos);
	}

	@Override
	public boolean isChild() {
		// TODO Автоматически созданная заглушка метода
		return super.isChild();
	}

	@Override
	protected void onDeathUpdate() {
		// TODO Автоматически созданная заглушка метода
		super.onDeathUpdate();
	}

	@Override
	protected int decreaseAirSupply(int air) {
		// TODO Автоматически созданная заглушка метода
		return super.decreaseAirSupply(air);
	}

	@Override
	protected boolean isPlayer() {
		// TODO Автоматически созданная заглушка метода
		return super.isPlayer();
	}

	@Override
	public Random getRNG() {
		// TODO Автоматически созданная заглушка метода
		return super.getRNG();
	}

	@Override
	public EntityLivingBase getRevengeTarget() {
		// TODO Автоматически созданная заглушка метода
		return super.getRevengeTarget();
	}

	@Override
	public int getRevengeTimer() {
		// TODO Автоматически созданная заглушка метода
		return super.getRevengeTimer();
	}

	@Override
	public void setRevengeTarget(EntityLivingBase livingBase) {
		// TODO Автоматически созданная заглушка метода
		super.setRevengeTarget(livingBase);
	}

	@Override
	public EntityLivingBase getLastAttackedEntity() {
		// TODO Автоматически созданная заглушка метода
		return super.getLastAttackedEntity();
	}

	@Override
	public int getLastAttackedEntityTime() {
		// TODO Автоматически созданная заглушка метода
		return super.getLastAttackedEntityTime();
	}

	@Override
	public void setLastAttackedEntity(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.setLastAttackedEntity(entityIn);
	}

	@Override
	public int getIdleTime() {
		// TODO Автоматически созданная заглушка метода
		return super.getIdleTime();
	}

	@Override
	protected void playEquipSound(ItemStack stack) {
		// TODO Автоматически созданная заглушка метода
		super.playEquipSound(stack);
	}

	@Override
	protected void updatePotionEffects() {
		// TODO Автоматически созданная заглушка метода
		super.updatePotionEffects();
	}

	@Override
	protected void updatePotionMetadata() {
		// TODO Автоматически созданная заглушка метода
		super.updatePotionMetadata();
	}

	@Override
	protected void resetPotionEffectMetadata() {
		// TODO Автоматически созданная заглушка метода
		super.resetPotionEffectMetadata();
	}

	@Override
	public void clearActivePotions() {
		// TODO Автоматически созданная заглушка метода
		super.clearActivePotions();
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		// TODO Автоматически созданная заглушка метода
		return super.getActivePotionEffects();
	}

	@Override
	public Map<Potion, PotionEffect> getActivePotionMap() {
		// TODO Автоматически созданная заглушка метода
		return super.getActivePotionMap();
	}

	@Override
	public boolean isPotionActive(Potion potionIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isPotionActive(potionIn);
	}

	@Override
	public PotionEffect getActivePotionEffect(Potion potionIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getActivePotionEffect(potionIn);
	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		// TODO Автоматически созданная заглушка метода
		super.addPotionEffect(potioneffectIn);
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isPotionApplicable(potioneffectIn);
	}

	@Override
	public boolean isEntityUndead() {
		// TODO Автоматически созданная заглушка метода
		return super.isEntityUndead();
	}

	@Override
	public PotionEffect removeActivePotionEffect(Potion potioneffectin) {
		// TODO Автоматически созданная заглушка метода
		return super.removeActivePotionEffect(potioneffectin);
	}

	@Override
	public void removePotionEffect(Potion potionIn) {
		// TODO Автоматически созданная заглушка метода
		super.removePotionEffect(potionIn);
	}

	@Override
	protected void onNewPotionEffect(PotionEffect id) {
		// TODO Автоматически созданная заглушка метода
		super.onNewPotionEffect(id);
	}

	@Override
	protected void onChangedPotionEffect(PotionEffect id, boolean p_70695_2_) {
		// TODO Автоматически созданная заглушка метода
		super.onChangedPotionEffect(id, p_70695_2_);
	}

	@Override
	protected void onFinishedPotionEffect(PotionEffect effect) {
		// TODO Автоматически созданная заглушка метода
		super.onFinishedPotionEffect(effect);
	}

	@Override
	public void heal(float healAmount) {
		// TODO Автоматически созданная заглушка метода
		super.heal(healAmount);
	}

	@Override
	public void setHealth(float health) {
		// TODO Автоматически созданная заглушка метода
		super.setHealth(health);
	}

	@Override
	protected void blockUsingShield(EntityLivingBase p_190629_1_) {
		// TODO Автоматически созданная заглушка метода
		super.blockUsingShield(p_190629_1_);
	}

	@Override
	public DamageSource getLastDamageSource() {
		// TODO Автоматически созданная заглушка метода
		return super.getLastDamageSource();
	}

	@Override
	public void renderBrokenItemStack(ItemStack stack) {
		// TODO Автоматически созданная заглушка метода
		super.renderBrokenItemStack(stack);
	}

	@Override
	public void onDeath(DamageSource cause) {
		// TODO Автоматически созданная заглушка метода
		super.onDeath(cause);
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		// TODO Автоматически созданная заглушка метода
		super.knockBack(entityIn, strength, xRatio, zRatio);
	}

	@Override
	public boolean isOnLadder() {
		// TODO Автоматически созданная заглушка метода
		return super.isOnLadder();
	}

	@Override
	public boolean isEntityAlive() {
		// TODO Автоматически созданная заглушка метода
		return super.isEntityAlive();
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		// TODO Автоматически созданная заглушка метода
		super.fall(distance, damageMultiplier);
	}

	@Override
	public void performHurtAnimation() {
		// TODO Автоматически созданная заглушка метода
		super.performHurtAnimation();
	}

	@Override
	public int getTotalArmorValue() {
		// TODO Автоматически созданная заглушка метода
		return super.getTotalArmorValue();
	}

	@Override
	protected void damageArmor(float damage) {
		// TODO Автоматически созданная заглушка метода
		super.damageArmor(damage);
	}

	@Override
	protected void damageShield(float damage) {
		// TODO Автоматически созданная заглушка метода
		super.damageShield(damage);
	}

	@Override
	protected float applyArmorCalculations(DamageSource source, float damage) {
		// TODO Автоматически созданная заглушка метода
		return super.applyArmorCalculations(source, damage);
	}

	@Override
	protected float applyPotionDamageCalculations(DamageSource source, float damage) {
		// TODO Автоматически созданная заглушка метода
		return super.applyPotionDamageCalculations(source, damage);
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		// TODO Автоматически созданная заглушка метода
		super.damageEntity(damageSrc, damageAmount);
	}

	@Override
	public CombatTracker getCombatTracker() {
		// TODO Автоматически созданная заглушка метода
		return super.getCombatTracker();
	}

	@Override
	public EntityLivingBase getAttackingEntity() {
		// TODO Автоматически созданная заглушка метода
		return super.getAttackingEntity();
	}

	@Override
	public void swingArm(EnumHand hand) {
		// TODO Автоматически созданная заглушка метода
		super.swingArm(hand);
	}

	@Override
	protected void outOfWorld() {
		// TODO Автоматически созданная заглушка метода
		super.outOfWorld();
	}

	@Override
	protected void updateArmSwingProgress() {
		// TODO Автоматически созданная заглушка метода
		super.updateArmSwingProgress();
	}

	@Override
	public IAttributeInstance getEntityAttribute(IAttribute attribute) {
		// TODO Автоматически созданная заглушка метода
		return super.getEntityAttribute(attribute);
	}

	@Override
	public AbstractAttributeMap getAttributeMap() {
		// TODO Автоматически созданная заглушка метода
		return super.getAttributeMap();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		// TODO Автоматически созданная заглушка метода
		return super.getCreatureAttribute();
	}

	@Override
	public ItemStack getHeldItemMainhand() {
		// TODO Автоматически созданная заглушка метода
		return super.getHeldItemMainhand();
	}

	@Override
	public ItemStack getHeldItemOffhand() {
		// TODO Автоматически созданная заглушка метода
		return super.getHeldItemOffhand();
	}

	@Override
	public ItemStack getHeldItem(EnumHand hand) {
		// TODO Автоматически созданная заглушка метода
		return super.getHeldItem(hand);
	}

	@Override
	public void setHeldItem(EnumHand hand, ItemStack stack) {
		// TODO Автоматически созданная заглушка метода
		super.setHeldItem(hand, stack);
	}

	@Override
	public boolean hasItemInSlot(EntityEquipmentSlot p_190630_1_) {
		// TODO Автоматически созданная заглушка метода
		return super.hasItemInSlot(p_190630_1_);
	}

	@Override
	public void setSprinting(boolean sprinting) {
		// TODO Автоматически созданная заглушка метода
		super.setSprinting(sprinting);
	}

	@Override
	protected float getSoundVolume() {
		// TODO Автоматически созданная заглушка метода
		return super.getSoundVolume();
	}

	@Override
	protected float getSoundPitch() {
		// TODO Автоматически созданная заглушка метода
		return super.getSoundPitch();
	}

	@Override
	protected boolean isMovementBlocked() {
		// TODO Автоматически созданная заглушка метода
		return super.isMovementBlocked();
	}

	@Override
	public void dismountEntity(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.dismountEntity(entityIn);
	}

	@Override
	public boolean getAlwaysRenderNameTagForRender() {
		// TODO Автоматически созданная заглушка метода
		return super.getAlwaysRenderNameTagForRender();
	}

	@Override
	protected float getJumpUpwardsMotion() {
		// TODO Автоматически созданная заглушка метода
		return super.getJumpUpwardsMotion();
	}

	@Override
	protected void jump() {
		// TODO Автоматически созданная заглушка метода
		super.jump();
	}

	@Override
	protected void handleJumpWater() {
		// TODO Автоматически созданная заглушка метода
		super.handleJumpWater();
	}

	@Override
	protected void handleJumpLava() {
		// TODO Автоматически созданная заглушка метода
		super.handleJumpLava();
	}

	@Override
	protected float getWaterSlowDown() {
		// TODO Автоматически созданная заглушка метода
		return super.getWaterSlowDown();
	}

	@Override
	public void travel(float strafe, float vertical, float forward) {
		// TODO Автоматически созданная заглушка метода
		super.travel(strafe, vertical, forward);
	}

	@Override
	public float getAIMoveSpeed() {
		// TODO Автоматически созданная заглушка метода
		return super.getAIMoveSpeed();
	}

	@Override
	public boolean isPlayerSleeping() {
		// TODO Автоматически созданная заглушка метода
		return super.isPlayerSleeping();
	}

	@Override
	protected void collideWithNearbyEntities() {
		// TODO Автоматически созданная заглушка метода
		super.collideWithNearbyEntities();
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.collideWithEntity(entityIn);
	}

	@Override
	public void dismountRidingEntity() {
		// TODO Автоматически созданная заглушка метода
		super.dismountRidingEntity();
	}

	@Override
	public void updateRidden() {
		// TODO Автоматически созданная заглушка метода
		super.updateRidden();
	}

	@Override
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		// TODO Автоматически созданная заглушка метода
		super.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
	}

	@Override
	public void setJumping(boolean jumping) {
		// TODO Автоматически созданная заглушка метода
		super.setJumping(jumping);
	}

	@Override
	public void onItemPickup(Entity entityIn, int quantity) {
		// TODO Автоматически созданная заглушка метода
		super.onItemPickup(entityIn, quantity);
	}

	@Override
	public boolean canEntityBeSeen(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.canEntityBeSeen(entityIn);
	}

	@Override
	public Vec3d getLook(float partialTicks) {
		// TODO Автоматически созданная заглушка метода
		return super.getLook(partialTicks);
	}

	@Override
	public float getSwingProgress(float partialTickTime) {
		// TODO Автоматически созданная заглушка метода
		return super.getSwingProgress(partialTickTime);
	}

	@Override
	public boolean canBeCollidedWith() {
		// TODO Автоматически созданная заглушка метода
		return super.canBeCollidedWith();
	}

	@Override
	public boolean canBePushed() {
		// TODO Автоматически созданная заглушка метода
		return super.canBePushed();
	}

	@Override
	protected void markVelocityChanged() {
		// TODO Автоматически созданная заглушка метода
		super.markVelocityChanged();
	}

	@Override
	public float getRotationYawHead() {
		// TODO Автоматически созданная заглушка метода
		return super.getRotationYawHead();
	}

	@Override
	public void setRotationYawHead(float rotation) {
		// TODO Автоматически созданная заглушка метода
		super.setRotationYawHead(rotation);
	}

	@Override
	public void setRenderYawOffset(float offset) {
		// TODO Автоматически созданная заглушка метода
		super.setRenderYawOffset(offset);
	}

	@Override
	public float getAbsorptionAmount() {
		// TODO Автоматически созданная заглушка метода
		return super.getAbsorptionAmount();
	}

	@Override
	public void setAbsorptionAmount(float amount) {
		// TODO Автоматически созданная заглушка метода
		super.setAbsorptionAmount(amount);
	}

	@Override
	public void sendEnterCombat() {
		// TODO Автоматически созданная заглушка метода
		super.sendEnterCombat();
	}

	@Override
	public void sendEndCombat() {
		// TODO Автоматически созданная заглушка метода
		super.sendEndCombat();
	}

	@Override
	protected void markPotionsDirty() {
		// TODO Автоматически созданная заглушка метода
		super.markPotionsDirty();
	}

	@Override
	public void curePotionEffects(ItemStack curativeItem) {
		// TODO Автоматически созданная заглушка метода
		super.curePotionEffects(curativeItem);
	}

	@Override
	public boolean shouldRiderFaceForward(EntityPlayer player) {
		// TODO Автоматически созданная заглушка метода
		return super.shouldRiderFaceForward(player);
	}

	@Override
	public boolean isHandActive() {
		// TODO Автоматически созданная заглушка метода
		return super.isHandActive();
	}

	@Override
	public EnumHand getActiveHand() {
		// TODO Автоматически созданная заглушка метода
		return super.getActiveHand();
	}

	@Override
	protected void updateActiveHand() {
		// TODO Автоматически созданная заглушка метода
		super.updateActiveHand();
	}

	@Override
	public void setActiveHand(EnumHand hand) {
		// TODO Автоматически созданная заглушка метода
		super.setActiveHand(hand);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		// TODO Автоматически созданная заглушка метода
		super.notifyDataManagerChange(key);
	}

	@Override
	protected void updateItemUse(ItemStack stack, int eatingParticleCount) {
		// TODO Автоматически созданная заглушка метода
		super.updateItemUse(stack, eatingParticleCount);
	}

	@Override
	protected void onItemUseFinish() {
		// TODO Автоматически созданная заглушка метода
		super.onItemUseFinish();
	}

	@Override
	public ItemStack getActiveItemStack() {
		// TODO Автоматически созданная заглушка метода
		return super.getActiveItemStack();
	}

	@Override
	public int getItemInUseCount() {
		// TODO Автоматически созданная заглушка метода
		return super.getItemInUseCount();
	}

	@Override
	public int getItemInUseMaxCount() {
		// TODO Автоматически созданная заглушка метода
		return super.getItemInUseMaxCount();
	}

	@Override
	public void stopActiveHand() {
		// TODO Автоматически созданная заглушка метода
		super.stopActiveHand();
	}

	@Override
	public void resetActiveHand() {
		// TODO Автоматически созданная заглушка метода
		super.resetActiveHand();
	}

	@Override
	public boolean isActiveItemStackBlocking() {
		// TODO Автоматически созданная заглушка метода
		return super.isActiveItemStackBlocking();
	}

	@Override
	public boolean isElytraFlying() {
		// TODO Автоматически созданная заглушка метода
		return super.isElytraFlying();
	}

	@Override
	public int getTicksElytraFlying() {
		// TODO Автоматически созданная заглушка метода
		return super.getTicksElytraFlying();
	}

	@Override
	public boolean attemptTeleport(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.attemptTeleport(x, y, z);
	}

	@Override
	public boolean canBeHitWithPotion() {
		// TODO Автоматически созданная заглушка метода
		return super.canBeHitWithPotion();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO Автоматически созданная заглушка метода
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO Автоматически созданная заглушка метода
		return super.hasCapability(capability, facing);
	}

	@Override
	public boolean attackable() {
		// TODO Автоматически созданная заглушка метода
		return super.attackable();
	}

	@Override
	public void setPartying(BlockPos pos, boolean p_191987_2_) {
		// TODO Автоматически созданная заглушка метода
		super.setPartying(pos, p_191987_2_);
	}

	@Override
	public int getEntityId() {
		// TODO Автоматически созданная заглушка метода
		return super.getEntityId();
	}

	@Override
	public void setEntityId(int id) {
		// TODO Автоматически созданная заглушка метода
		super.setEntityId(id);
	}

	@Override
	public Set<String> getTags() {
		// TODO Автоматически созданная заглушка метода
		return super.getTags();
	}

	@Override
	public boolean addTag(String tag) {
		// TODO Автоматически созданная заглушка метода
		return super.addTag(tag);
	}

	@Override
	public boolean removeTag(String tag) {
		// TODO Автоматически созданная заглушка метода
		return super.removeTag(tag);
	}

	@Override
	public EntityDataManager getDataManager() {
		// TODO Автоматически созданная заглушка метода
		return super.getDataManager();
	}

	@Override
	public boolean equals(Object p_equals_1_) {
		// TODO Автоматически созданная заглушка метода
		return super.equals(p_equals_1_);
	}

	@Override
	public int hashCode() {
		// TODO Автоматически созданная заглушка метода
		return super.hashCode();
	}

	@Override
	protected void preparePlayerToSpawn() {
		// TODO Автоматически созданная заглушка метода
		super.preparePlayerToSpawn();
	}

	@Override
	public void setDead() {
		// TODO Автоматически созданная заглушка метода
		super.setDead();
	}

	@Override
	public void setDropItemsWhenDead(boolean dropWhenDead) {
		// TODO Автоматически созданная заглушка метода
		super.setDropItemsWhenDead(dropWhenDead);
	}

	@Override
	protected void setSize(float width, float height) {
		// TODO Автоматически созданная заглушка метода
		super.setSize(width, height);
	}

	@Override
	protected void setRotation(float yaw, float pitch) {
		// TODO Автоматически созданная заглушка метода
		super.setRotation(yaw, pitch);
	}

	@Override
	public void setPosition(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		super.setPosition(x, y, z);
	}

	@Override
	public void turn(float yaw, float pitch) {
		// TODO Автоматически созданная заглушка метода
		super.turn(yaw, pitch);
	}

	@Override
	protected void decrementTimeUntilPortal() {
		// TODO Автоматически созданная заглушка метода
		super.decrementTimeUntilPortal();
	}

	@Override
	public int getMaxInPortalTime() {
		// TODO Автоматически созданная заглушка метода
		return super.getMaxInPortalTime();
	}

	@Override
	protected void setOnFireFromLava() {
		// TODO Автоматически созданная заглушка метода
		super.setOnFireFromLava();
	}

	@Override
	public void setFire(int seconds) {
		// TODO Автоматически созданная заглушка метода
		super.setFire(seconds);
	}

	@Override
	public void extinguish() {
		// TODO Автоматически созданная заглушка метода
		super.extinguish();
	}

	@Override
	public boolean isOffsetPositionInLiquid(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.isOffsetPositionInLiquid(x, y, z);
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		super.move(type, x, y, z);
	}

	@Override
	public void resetPositionToBB() {
		// TODO Автоматически созданная заглушка метода
		super.resetPositionToBB();
	}

	@Override
	protected void doBlockCollisions() {
		// TODO Автоматически созданная заглушка метода
		super.doBlockCollisions();
	}

	@Override
	protected void onInsideBlock(IBlockState p_191955_1_) {
		// TODO Автоматически созданная заглушка метода
		super.onInsideBlock(p_191955_1_);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		// TODO Автоматически созданная заглушка метода
		super.playStepSound(pos, blockIn);
	}

	@Override
	protected float playFlySound(float p_191954_1_) {
		// TODO Автоматически созданная заглушка метода
		return super.playFlySound(p_191954_1_);
	}

	@Override
	protected boolean makeFlySound() {
		// TODO Автоматически созданная заглушка метода
		return super.makeFlySound();
	}

	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch) {
		// TODO Автоматически созданная заглушка метода
		super.playSound(soundIn, volume, pitch);
	}

	@Override
	public boolean isSilent() {
		// TODO Автоматически созданная заглушка метода
		return super.isSilent();
	}

	@Override
	public void setSilent(boolean isSilent) {
		// TODO Автоматически созданная заглушка метода
		super.setSilent(isSilent);
	}

	@Override
	public boolean hasNoGravity() {
		// TODO Автоматически созданная заглушка метода
		return super.hasNoGravity();
	}

	@Override
	public void setNoGravity(boolean noGravity) {
		// TODO Автоматически созданная заглушка метода
		super.setNoGravity(noGravity);
	}

	@Override
	protected boolean canTriggerWalking() {
		// TODO Автоматически созданная заглушка метода
		return super.canTriggerWalking();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		// TODO Автоматически созданная заглушка метода
		return super.getCollisionBoundingBox();
	}

	@Override
	protected void dealFireDamage(int amount) {
		// TODO Автоматически созданная заглушка метода
		super.dealFireDamage(amount);
	}

	@Override
	public boolean isWet() {
		// TODO Автоматически созданная заглушка метода
		return super.isWet();
	}

	@Override
	public boolean isInWater() {
		// TODO Автоматически созданная заглушка метода
		return super.isInWater();
	}

	@Override
	public boolean isOverWater() {
		// TODO Автоматически созданная заглушка метода
		return super.isOverWater();
	}

	@Override
	public boolean handleWaterMovement() {
		// TODO Автоматически созданная заглушка метода
		return super.handleWaterMovement();
	}

	@Override
	protected void doWaterSplashEffect() {
		// TODO Автоматически созданная заглушка метода
		super.doWaterSplashEffect();
	}

	@Override
	public void spawnRunningParticles() {
		// TODO Автоматически созданная заглушка метода
		super.spawnRunningParticles();
	}

	@Override
	protected void createRunningParticles() {
		// TODO Автоматически созданная заглушка метода
		super.createRunningParticles();
	}

	@Override
	public boolean isInsideOfMaterial(Material materialIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isInsideOfMaterial(materialIn);
	}

	@Override
	public boolean isInLava() {
		// TODO Автоматически созданная заглушка метода
		return super.isInLava();
	}

	@Override
	public void moveRelative(float strafe, float up, float forward, float friction) {
		// TODO Автоматически созданная заглушка метода
		super.moveRelative(strafe, up, forward, friction);
	}

	@Override
	public int getBrightnessForRender() {
		// TODO Автоматически созданная заглушка метода
		return super.getBrightnessForRender();
	}

	@Override
	public float getBrightness() {
		// TODO Автоматически созданная заглушка метода
		return super.getBrightness();
	}

	@Override
	public void setWorld(World worldIn) {
		// TODO Автоматически созданная заглушка метода
		super.setWorld(worldIn);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
		// TODO Автоматически созданная заглушка метода
		super.setPositionAndRotation(x, y, z, yaw, pitch);
	}

	@Override
	public void moveToBlockPosAndAngles(BlockPos pos, float rotationYawIn, float rotationPitchIn) {
		// TODO Автоматически созданная заглушка метода
		super.moveToBlockPosAndAngles(pos, rotationYawIn, rotationPitchIn);
	}

	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
		// TODO Автоматически созданная заглушка метода
		super.setLocationAndAngles(x, y, z, yaw, pitch);
	}

	@Override
	public float getDistance(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistance(entityIn);
	}

	@Override
	public double getDistanceSq(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistanceSq(x, y, z);
	}

	@Override
	public double getDistanceSq(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistanceSq(pos);
	}

	@Override
	public double getDistanceSqToCenter(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistanceSqToCenter(pos);
	}

	@Override
	public double getDistance(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistance(x, y, z);
	}

	@Override
	public double getDistanceSq(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getDistanceSq(entityIn);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.onCollideWithPlayer(entityIn);
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.applyEntityCollision(entityIn);
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		super.addVelocity(x, y, z);
	}

	@Override
	public Vec3d getPositionEyes(float partialTicks) {
		// TODO Автоматически созданная заглушка метода
		return super.getPositionEyes(partialTicks);
	}

	@Override
	public RayTraceResult rayTrace(double blockReachDistance, float partialTicks) {
		// TODO Автоматически созданная заглушка метода
		return super.rayTrace(blockReachDistance, partialTicks);
	}

	@Override
	public void awardKillScore(Entity p_191956_1_, int p_191956_2_, DamageSource p_191956_3_) {
		// TODO Автоматически созданная заглушка метода
		super.awardKillScore(p_191956_1_, p_191956_2_, p_191956_3_);
	}

	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.isInRangeToRender3d(x, y, z);
	}

	@Override
	public boolean isInRangeToRenderDist(double distance) {
		// TODO Автоматически созданная заглушка метода
		return super.isInRangeToRenderDist(distance);
	}

	@Override
	public boolean writeToNBTAtomically(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		return super.writeToNBTAtomically(compound);
	}

	@Override
	public boolean writeToNBTOptional(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		return super.writeToNBTOptional(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Автоматически созданная заглушка метода
		super.readFromNBT(compound);
	}

	@Override
	protected boolean shouldSetPosAfterLoading() {
		// TODO Автоматически созданная заглушка метода
		return super.shouldSetPosAfterLoading();
	}

	@Override
	protected NBTTagList newDoubleNBTList(double... numbers) {
		// TODO Автоматически созданная заглушка метода
		return super.newDoubleNBTList(numbers);
	}

	@Override
	protected NBTTagList newFloatNBTList(float... numbers) {
		// TODO Автоматически созданная заглушка метода
		return super.newFloatNBTList(numbers);
	}

	@Override
	public EntityItem dropItem(Item itemIn, int size) {
		// TODO Автоматически созданная заглушка метода
		return super.dropItem(itemIn, size);
	}

	@Override
	public EntityItem dropItemWithOffset(Item itemIn, int size, float offsetY) {
		// TODO Автоматически созданная заглушка метода
		return super.dropItemWithOffset(itemIn, size, offsetY);
	}

	@Override
	public EntityItem entityDropItem(ItemStack stack, float offsetY) {
		// TODO Автоматически созданная заглушка метода
		return super.entityDropItem(stack, offsetY);
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		// TODO Автоматически созданная заглушка метода
		return super.isEntityInsideOpaqueBlock();
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getCollisionBox(entityIn);
	}

	@Override
	public void updatePassenger(Entity passenger) {
		// TODO Автоматически созданная заглушка метода
		super.updatePassenger(passenger);
	}

	@Override
	public void applyOrientationToEntity(Entity entityToUpdate) {
		// TODO Автоматически созданная заглушка метода
		super.applyOrientationToEntity(entityToUpdate);
	}

	@Override
	public double getYOffset() {
		// TODO Автоматически созданная заглушка метода
		return super.getYOffset();
	}

	@Override
	public double getMountedYOffset() {
		// TODO Автоматически созданная заглушка метода
		return super.getMountedYOffset();
	}

	@Override
	public boolean startRiding(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.startRiding(entityIn);
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.canBeRidden(entityIn);
	}

	@Override
	public void removePassengers() {
		// TODO Автоматически созданная заглушка метода
		super.removePassengers();
	}

	@Override
	protected void addPassenger(Entity passenger) {
		// TODO Автоматически созданная заглушка метода
		super.addPassenger(passenger);
	}

	@Override
	protected void removePassenger(Entity passenger) {
		// TODO Автоматически созданная заглушка метода
		super.removePassenger(passenger);
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		// TODO Автоматически созданная заглушка метода
		return super.canFitPassenger(passenger);
	}

	@Override
	public float getCollisionBorderSize() {
		// TODO Автоматически созданная заглушка метода
		return super.getCollisionBorderSize();
	}

	@Override
	public Vec3d getLookVec() {
		// TODO Автоматически созданная заглушка метода
		return super.getLookVec();
	}

	@Override
	public Vec2f getPitchYaw() {
		// TODO Автоматически созданная заглушка метода
		return super.getPitchYaw();
	}

	@Override
	public Vec3d getForward() {
		// TODO Автоматически созданная заглушка метода
		return super.getForward();
	}

	@Override
	public void setPortal(BlockPos pos) {
		// TODO Автоматически созданная заглушка метода
		super.setPortal(pos);
	}

	@Override
	public int getPortalCooldown() {
		// TODO Автоматически созданная заглушка метода
		return super.getPortalCooldown();
	}

	@Override
	public void setVelocity(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		super.setVelocity(x, y, z);
	}

	@Override
	public Iterable<ItemStack> getEquipmentAndArmor() {
		// TODO Автоматически созданная заглушка метода
		return super.getEquipmentAndArmor();
	}

	@Override
	public boolean isBurning() {
		// TODO Автоматически созданная заглушка метода
		return super.isBurning();
	}

	@Override
	public boolean isRiding() {
		// TODO Автоматически созданная заглушка метода
		return super.isRiding();
	}

	@Override
	public boolean isBeingRidden() {
		// TODO Автоматически созданная заглушка метода
		return super.isBeingRidden();
	}

	@Override
	public boolean isSneaking() {
		// TODO Автоматически созданная заглушка метода
		return super.isSneaking();
	}

	@Override
	public void setSneaking(boolean sneaking) {
		// TODO Автоматически созданная заглушка метода
		super.setSneaking(sneaking);
	}

	@Override
	public boolean isSprinting() {
		// TODO Автоматически созданная заглушка метода
		return super.isSprinting();
	}

	@Override
	public boolean isGlowing() {
		// TODO Автоматически созданная заглушка метода
		return super.isGlowing();
	}

	@Override
	public void setGlowing(boolean glowingIn) {
		// TODO Автоматически созданная заглушка метода
		super.setGlowing(glowingIn);
	}

	@Override
	public boolean isInvisible() {
		// TODO Автоматически созданная заглушка метода
		return super.isInvisible();
	}

	@Override
	public boolean isInvisibleToPlayer(EntityPlayer player) {
		// TODO Автоматически созданная заглушка метода
		return super.isInvisibleToPlayer(player);
	}

	@Override
	public Team getTeam() {
		// TODO Автоматически созданная заглушка метода
		return super.getTeam();
	}

	@Override
	public boolean isOnSameTeam(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isOnSameTeam(entityIn);
	}

	@Override
	public boolean isOnScoreboardTeam(Team teamIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isOnScoreboardTeam(teamIn);
	}

	@Override
	public void setInvisible(boolean invisible) {
		// TODO Автоматически созданная заглушка метода
		super.setInvisible(invisible);
	}

	@Override
	protected boolean getFlag(int flag) {
		// TODO Автоматически созданная заглушка метода
		return super.getFlag(flag);
	}

	@Override
	protected void setFlag(int flag, boolean set) {
		// TODO Автоматически созданная заглушка метода
		super.setFlag(flag, set);
	}

	@Override
	public int getAir() {
		// TODO Автоматически созданная заглушка метода
		return super.getAir();
	}

	@Override
	public void setAir(int air) {
		// TODO Автоматически созданная заглушка метода
		super.setAir(air);
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		// TODO Автоматически созданная заглушка метода
		super.onStruckByLightning(lightningBolt);
	}

	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn) {
		// TODO Автоматически созданная заглушка метода
		super.onKillEntity(entityLivingIn);
	}

	@Override
	protected boolean pushOutOfBlocks(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		return super.pushOutOfBlocks(x, y, z);
	}

	@Override
	public void setInWeb() {
		// TODO Автоматически созданная заглушка метода
		super.setInWeb();
	}

	@Override
	public String getName() {
		// TODO Автоматически созданная заглушка метода
		return super.getName();
	}

	@Override
	public Entity[] getParts() {
		// TODO Автоматически созданная заглушка метода
		return super.getParts();
	}

	@Override
	public boolean isEntityEqual(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isEntityEqual(entityIn);
	}

	@Override
	public boolean canBeAttackedWithItem() {
		// TODO Автоматически созданная заглушка метода
		return super.canBeAttackedWithItem();
	}

	@Override
	public boolean hitByEntity(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.hitByEntity(entityIn);
	}

	@Override
	public String toString() {
		// TODO Автоматически созданная заглушка метода
		return super.toString();
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		// TODO Автоматически созданная заглушка метода
		return super.isEntityInvulnerable(source);
	}

	@Override
	public boolean getIsInvulnerable() {
		// TODO Автоматически созданная заглушка метода
		return super.getIsInvulnerable();
	}

	@Override
	public void setEntityInvulnerable(boolean isInvulnerable) {
		// TODO Автоматически созданная заглушка метода
		super.setEntityInvulnerable(isInvulnerable);
	}

	@Override
	public void copyLocationAndAnglesFrom(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.copyLocationAndAnglesFrom(entityIn);
	}

	@Override
	public Entity changeDimension(int dimensionIn) {
		// TODO Автоматически созданная заглушка метода
		return super.changeDimension(dimensionIn);
	}

	@Override
	public Entity changeDimension(int dimensionIn, ITeleporter teleporter) {
		// TODO Автоматически созданная заглушка метода
		return super.changeDimension(dimensionIn, teleporter);
	}

	@Override
	public boolean isNonBoss() {
		// TODO Автоматически созданная заглушка метода
		return super.isNonBoss();
	}

	@Override
	public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn) {
		// TODO Автоматически созданная заглушка метода
		return super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
	}

	@Override
	public boolean canExplosionDestroyBlock(Explosion explosionIn, World worldIn, BlockPos pos,
			IBlockState blockStateIn, float p_174816_5_) {
		// TODO Автоматически созданная заглушка метода
		return super.canExplosionDestroyBlock(explosionIn, worldIn, pos, blockStateIn, p_174816_5_);
	}

	@Override
	public Vec3d getLastPortalVec() {
		// TODO Автоматически созданная заглушка метода
		return super.getLastPortalVec();
	}

	@Override
	public EnumFacing getTeleportDirection() {
		// TODO Автоматически созданная заглушка метода
		return super.getTeleportDirection();
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		// TODO Автоматически созданная заглушка метода
		return super.doesEntityNotTriggerPressurePlate();
	}

	@Override
	public void addEntityCrashInfo(CrashReportCategory category) {
		// TODO Автоматически созданная заглушка метода
		super.addEntityCrashInfo(category);
	}

	@Override
	public void setUniqueId(UUID uniqueIdIn) {
		// TODO Автоматически созданная заглушка метода
		super.setUniqueId(uniqueIdIn);
	}

	@Override
	public boolean canRenderOnFire() {
		// TODO Автоматически созданная заглушка метода
		return super.canRenderOnFire();
	}

	@Override
	public UUID getUniqueID() {
		// TODO Автоматически созданная заглушка метода
		return super.getUniqueID();
	}

	@Override
	public String getCachedUniqueIdString() {
		// TODO Автоматически созданная заглушка метода
		return super.getCachedUniqueIdString();
	}

	@Override
	public boolean isPushedByWater() {
		// TODO Автоматически созданная заглушка метода
		return super.isPushedByWater();
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Автоматически созданная заглушка метода
		return super.getDisplayName();
	}

	@Override
	public void setCustomNameTag(String name) {
		// TODO Автоматически созданная заглушка метода
		super.setCustomNameTag(name);
	}

	@Override
	public String getCustomNameTag() {
		// TODO Автоматически созданная заглушка метода
		return super.getCustomNameTag();
	}

	@Override
	public boolean hasCustomName() {
		// TODO Автоматически созданная заглушка метода
		return super.hasCustomName();
	}

	@Override
	public void setAlwaysRenderNameTag(boolean alwaysRenderNameTag) {
		// TODO Автоматически созданная заглушка метода
		super.setAlwaysRenderNameTag(alwaysRenderNameTag);
	}

	@Override
	public boolean getAlwaysRenderNameTag() {
		// TODO Автоматически созданная заглушка метода
		return super.getAlwaysRenderNameTag();
	}

	@Override
	public void setPositionAndUpdate(double x, double y, double z) {
		// TODO Автоматически созданная заглушка метода
		super.setPositionAndUpdate(x, y, z);
	}

	@Override
	public EnumFacing getHorizontalFacing() {
		// TODO Автоматически созданная заглушка метода
		return super.getHorizontalFacing();
	}

	@Override
	public EnumFacing getAdjustedHorizontalFacing() {
		// TODO Автоматически созданная заглушка метода
		return super.getAdjustedHorizontalFacing();
	}

	@Override
	protected HoverEvent getHoverEvent() {
		// TODO Автоматически созданная заглушка метода
		return super.getHoverEvent();
	}

	@Override
	public boolean isSpectatedByPlayer(EntityPlayerMP player) {
		// TODO Автоматически созданная заглушка метода
		return super.isSpectatedByPlayer(player);
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		// TODO Автоматически созданная заглушка метода
		return super.getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		// TODO Автоматически созданная заглушка метода
		return super.getRenderBoundingBox();
	}

	@Override
	public void setEntityBoundingBox(AxisAlignedBB bb) {
		// TODO Автоматически созданная заглушка метода
		super.setEntityBoundingBox(bb);
	}

	@Override
	public float getEyeHeight() {
		// TODO Автоматически созданная заглушка метода
		return super.getEyeHeight();
	}

	@Override
	public boolean isOutsideBorder() {
		// TODO Автоматически созданная заглушка метода
		return super.isOutsideBorder();
	}

	@Override
	public void setOutsideBorder(boolean outsideBorder) {
		// TODO Автоматически созданная заглушка метода
		super.setOutsideBorder(outsideBorder);
	}

	@Override
	public void sendMessage(ITextComponent component) {
		// TODO Автоматически созданная заглушка метода
		super.sendMessage(component);
	}

	@Override
	public boolean canUseCommand(int permLevel, String commandName) {
		// TODO Автоматически созданная заглушка метода
		return super.canUseCommand(permLevel, commandName);
	}

	@Override
	public BlockPos getPosition() {
		// TODO Автоматически созданная заглушка метода
		return super.getPosition();
	}

	@Override
	public Vec3d getPositionVector() {
		// TODO Автоматически созданная заглушка метода
		return super.getPositionVector();
	}

	@Override
	public World getEntityWorld() {
		// TODO Автоматически созданная заглушка метода
		return super.getEntityWorld();
	}

	@Override
	public Entity getCommandSenderEntity() {
		// TODO Автоматически созданная заглушка метода
		return super.getCommandSenderEntity();
	}

	@Override
	public boolean sendCommandFeedback() {
		// TODO Автоматически созданная заглушка метода
		return super.sendCommandFeedback();
	}

	@Override
	public void setCommandStat(Type type, int amount) {
		// TODO Автоматически созданная заглушка метода
		super.setCommandStat(type, amount);
	}

	@Override
	public MinecraftServer getServer() {
		// TODO Автоматически созданная заглушка метода
		return super.getServer();
	}

	@Override
	public CommandResultStats getCommandStats() {
		// TODO Автоматически созданная заглушка метода
		return super.getCommandStats();
	}

	@Override
	public void setCommandStats(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.setCommandStats(entityIn);
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		// TODO Автоматически созданная заглушка метода
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public boolean isImmuneToExplosions() {
		// TODO Автоматически созданная заглушка метода
		return super.isImmuneToExplosions();
	}

	@Override
	protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		super.applyEnchantments(entityLivingBaseIn, entityIn);
	}

	@Override
	public NBTTagCompound getEntityData() {
		// TODO Автоматически созданная заглушка метода
		return super.getEntityData();
	}

	@Override
	public boolean shouldRiderSit() {
		// TODO Автоматически созданная заглушка метода
		return super.shouldRiderSit();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		// TODO Автоматически созданная заглушка метода
		return super.getPickedResult(target);
	}

	@Override
	public UUID getPersistentID() {
		// TODO Автоматически созданная заглушка метода
		return super.getPersistentID();
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		// TODO Автоматически созданная заглушка метода
		return super.shouldRenderInPass(pass);
	}

	@Override
	public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
		// TODO Автоматически созданная заглушка метода
		return super.isCreatureType(type, forSpawnCount);
	}

	@Override
	public boolean canRiderInteract() {
		// TODO Автоматически созданная заглушка метода
		return super.canRiderInteract();
	}

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		// TODO Автоматически созданная заглушка метода
		return super.shouldDismountInWater(rider);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		// TODO Автоматически созданная заглушка метода
		super.deserializeNBT(nbt);
	}

	@Override
	public NBTTagCompound serializeNBT() {
		// TODO Автоматически созданная заглушка метода
		return super.serializeNBT();
	}

	@Override
	public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance) {
		// TODO Автоматически созданная заглушка метода
		return super.canTrample(world, block, pos, fallDistance);
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		// TODO Автоматически созданная заглушка метода
		super.addTrackingPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		// TODO Автоматически созданная заглушка метода
		super.removeTrackingPlayer(player);
	}

	@Override
	public float getRotatedYaw(Rotation transformRotation) {
		// TODO Автоматически созданная заглушка метода
		return super.getRotatedYaw(transformRotation);
	}

	@Override
	public float getMirroredYaw(Mirror transformMirror) {
		// TODO Автоматически созданная заглушка метода
		return super.getMirroredYaw(transformMirror);
	}

	@Override
	public boolean ignoreItemEntityData() {
		// TODO Автоматически созданная заглушка метода
		return super.ignoreItemEntityData();
	}

	@Override
	public boolean setPositionNonDirty() {
		// TODO Автоматически созданная заглушка метода
		return super.setPositionNonDirty();
	}

	@Override
	public Entity getControllingPassenger() {
		// TODO Автоматически созданная заглушка метода
		return super.getControllingPassenger();
	}

	@Override
	public List<Entity> getPassengers() {
		// TODO Автоматически созданная заглушка метода
		return super.getPassengers();
	}

	@Override
	public boolean isPassenger(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isPassenger(entityIn);
	}

	@Override
	public Collection<Entity> getRecursivePassengers() {
		// TODO Автоматически созданная заглушка метода
		return super.getRecursivePassengers();
	}

	@Override
	public <T extends Entity> Collection<T> getRecursivePassengersByType(Class<T> entityClass) {
		// TODO Автоматически созданная заглушка метода
		return super.getRecursivePassengersByType(entityClass);
	}

	@Override
	public Entity getLowestRidingEntity() {
		// TODO Автоматически созданная заглушка метода
		return super.getLowestRidingEntity();
	}

	@Override
	public boolean isRidingSameEntity(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isRidingSameEntity(entityIn);
	}

	@Override
	public boolean isRidingOrBeingRiddenBy(Entity entityIn) {
		// TODO Автоматически созданная заглушка метода
		return super.isRidingOrBeingRiddenBy(entityIn);
	}

	@Override
	public Entity getRidingEntity() {
		// TODO Автоматически созданная заглушка метода
		return super.getRidingEntity();
	}

	@Override
	public EnumPushReaction getPushReaction() {
		// TODO Автоматически созданная заглушка метода
		return super.getPushReaction();
	}

	@Override
	protected int getFireImmuneTicks() {
		// TODO Автоматически созданная заглушка метода
		return super.getFireImmuneTicks();
	}

	public Vvsvsvsd(World worldIn) {
		super(worldIn);
		// TODO Автоматически созданная заглушка конструктора
	}

}
