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
		// TODO ������������� ��������� �������� ������
		return super.getSoundCategory();
	}

	@Override
	public void onLivingUpdate() {
		// TODO ������������� ��������� �������� ������
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		// TODO ������������� ��������� �������� ������
		super.onUpdate();
	}

	@Override
	protected SoundEvent getSwimSound() {
		// TODO ������������� ��������� �������� ������
		return super.getSwimSound();
	}

	@Override
	protected SoundEvent getSplashSound() {
		// TODO ������������� ��������� �������� ������
		return super.getSplashSound();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO ������������� ��������� �������� ������
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		// TODO ������������� ��������� �������� ������
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO ������������� ��������� �������� ������
		return super.getDeathSound();
	}

	@Override
	protected SoundEvent getFallSound(int heightIn) {
		// TODO ������������� ��������� �������� ������
		return super.getFallSound(heightIn);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		return super.getBlockPathWeight(pos);
	}

	@Override
	protected boolean isValidLightLevel() {
		// TODO ������������� ��������� �������� ������
		return super.isValidLightLevel();
	}

	@Override
	public boolean getCanSpawnHere() {
		// TODO ������������� ��������� �������� ������
		return super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		// TODO ������������� ��������� �������� ������
		super.applyEntityAttributes();
	}

	@Override
	protected boolean canDropLoot() {
		// TODO ������������� ��������� �������� ������
		return super.canDropLoot();
	}

	@Override
	public boolean isPreventingPlayerRest(EntityPlayer playerIn) {
		// TODO ������������� ��������� �������� ������
		return super.isPreventingPlayerRest(playerIn);
	}

	@Override
	public boolean hasPath() {
		// TODO ������������� ��������� �������� ������
		return super.hasPath();
	}

	@Override
	public boolean isWithinHomeDistanceCurrentPosition() {
		// TODO ������������� ��������� �������� ������
		return super.isWithinHomeDistanceCurrentPosition();
	}

	@Override
	public boolean isWithinHomeDistanceFromPosition(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		return super.isWithinHomeDistanceFromPosition(pos);
	}

	@Override
	public void setHomePosAndDistance(BlockPos pos, int distance) {
		// TODO ������������� ��������� �������� ������
		super.setHomePosAndDistance(pos, distance);
	}

	@Override
	public BlockPos getHomePosition() {
		// TODO ������������� ��������� �������� ������
		return super.getHomePosition();
	}

	@Override
	public float getMaximumHomeDistance() {
		// TODO ������������� ��������� �������� ������
		return super.getMaximumHomeDistance();
	}

	@Override
	public void detachHome() {
		// TODO ������������� ��������� �������� ������
		super.detachHome();
	}

	@Override
	public boolean hasHome() {
		// TODO ������������� ��������� �������� ������
		return super.hasHome();
	}

	@Override
	protected void updateLeashedState() {
		// TODO ������������� ��������� �������� ������
		super.updateLeashedState();
	}

	@Override
	protected double followLeashSpeed() {
		// TODO ������������� ��������� �������� ������
		return super.followLeashSpeed();
	}

	@Override
	protected void onLeashDistance(float p_142017_1_) {
		// TODO ������������� ��������� �������� ������
		super.onLeashDistance(p_142017_1_);
	}

	@Override
	protected void initEntityAI() {
		// TODO ������������� ��������� �������� ������
		super.initEntityAI();
	}

	@Override
	protected PathNavigate createNavigator(World worldIn) {
		// TODO ������������� ��������� �������� ������
		return super.createNavigator(worldIn);
	}

	@Override
	public float getPathPriority(PathNodeType nodeType) {
		// TODO ������������� ��������� �������� ������
		return super.getPathPriority(nodeType);
	}

	@Override
	public void setPathPriority(PathNodeType nodeType, float priority) {
		// TODO ������������� ��������� �������� ������
		super.setPathPriority(nodeType, priority);
	}

	@Override
	protected EntityBodyHelper createBodyHelper() {
		// TODO ������������� ��������� �������� ������
		return super.createBodyHelper();
	}

	@Override
	public EntityLookHelper getLookHelper() {
		// TODO ������������� ��������� �������� ������
		return super.getLookHelper();
	}

	@Override
	public EntityMoveHelper getMoveHelper() {
		// TODO ������������� ��������� �������� ������
		return super.getMoveHelper();
	}

	@Override
	public EntityJumpHelper getJumpHelper() {
		// TODO ������������� ��������� �������� ������
		return super.getJumpHelper();
	}

	@Override
	public PathNavigate getNavigator() {
		// TODO ������������� ��������� �������� ������
		return super.getNavigator();
	}

	@Override
	public EntitySenses getEntitySenses() {
		// TODO ������������� ��������� �������� ������
		return super.getEntitySenses();
	}

	@Override
	public EntityLivingBase getAttackTarget() {
		// TODO ������������� ��������� �������� ������
		return super.getAttackTarget();
	}

	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		// TODO ������������� ��������� �������� ������
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
		// TODO ������������� ��������� �������� ������
		return super.canAttackClass(cls);
	}

	@Override
	public void eatGrassBonus() {
		// TODO ������������� ��������� �������� ������
		super.eatGrassBonus();
	}

	@Override
	protected void entityInit() {
		// TODO ������������� ��������� �������� ������
		super.entityInit();
	}

	@Override
	public int getTalkInterval() {
		// TODO ������������� ��������� �������� ������
		return super.getTalkInterval();
	}

	@Override
	public void playLivingSound() {
		// TODO ������������� ��������� �������� ������
		super.playLivingSound();
	}

	@Override
	public void onEntityUpdate() {
		// TODO ������������� ��������� �������� ������
		super.onEntityUpdate();
	}

	@Override
	protected void playHurtSound(DamageSource source) {
		// TODO ������������� ��������� �������� ������
		super.playHurtSound(source);
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		// TODO ������������� ��������� �������� ������
		return super.getExperiencePoints(player);
	}

	@Override
	public void spawnExplosionParticle() {
		// TODO ������������� ��������� �������� ������
		super.spawnExplosionParticle();
	}

	@Override
	public void handleStatusUpdate(byte id) {
		// TODO ������������� ��������� �������� ������
		super.handleStatusUpdate(id);
	}

	@Override
	protected float updateDistance(float p_110146_1_, float p_110146_2_) {
		// TODO ������������� ��������� �������� ������
		return super.updateDistance(p_110146_1_, p_110146_2_);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		// TODO ������������� ��������� �������� ������
		return super.getAmbientSound();
	}

	@Override
	protected Item getDropItem() {
		// TODO ������������� ��������� �������� ������
		return super.getDropItem();
	}

	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		// TODO ������������� ��������� �������� ������
		super.dropFewItems(wasRecentlyHit, lootingModifier);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		super.readEntityFromNBT(compound);
	}

	@Override
	protected ResourceLocation getLootTable() {
		// TODO ������������� ��������� �������� ������
		return super.getLootTable();
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		// TODO ������������� ��������� �������� ������
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}

	@Override
	public void setMoveForward(float amount) {
		// TODO ������������� ��������� �������� ������
		super.setMoveForward(amount);
	}

	@Override
	public void setMoveVertical(float amount) {
		// TODO ������������� ��������� �������� ������
		super.setMoveVertical(amount);
	}

	@Override
	public void setMoveStrafing(float amount) {
		// TODO ������������� ��������� �������� ������
		super.setMoveStrafing(amount);
	}

	@Override
	public void setAIMoveSpeed(float speedIn) {
		// TODO ������������� ��������� �������� ������
		super.setAIMoveSpeed(speedIn);
	}

	@Override
	protected void updateEquipmentIfNeeded(EntityItem itemEntity) {
		// TODO ������������� ��������� �������� ������
		super.updateEquipmentIfNeeded(itemEntity);
	}

	@Override
	protected boolean canEquipItem(ItemStack stack) {
		// TODO ������������� ��������� �������� ������
		return super.canEquipItem(stack);
	}

	@Override
	protected boolean canDespawn() {
		// TODO ������������� ��������� �������� ������
		return super.canDespawn();
	}

	@Override
	protected void despawnEntity() {
		// TODO ������������� ��������� �������� ������
		super.despawnEntity();
	}

	@Override
	protected void updateAITasks() {
		// TODO ������������� ��������� �������� ������
		super.updateAITasks();
	}

	@Override
	public int getVerticalFaceSpeed() {
		// TODO ������������� ��������� �������� ������
		return super.getVerticalFaceSpeed();
	}

	@Override
	public int getHorizontalFaceSpeed() {
		// TODO ������������� ��������� �������� ������
		return super.getHorizontalFaceSpeed();
	}

	@Override
	public void faceEntity(Entity entityIn, float maxYawIncrease, float maxPitchIncrease) {
		// TODO ������������� ��������� �������� ������
		super.faceEntity(entityIn, maxYawIncrease, maxPitchIncrease);
	}

	@Override
	public boolean isNotColliding() {
		// TODO ������������� ��������� �������� ������
		return super.isNotColliding();
	}

	@Override
	public float getRenderSizeModifier() {
		// TODO ������������� ��������� �������� ������
		return super.getRenderSizeModifier();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		// TODO ������������� ��������� �������� ������
		return super.getMaxSpawnedInChunk();
	}

	@Override
	public int getMaxFallHeight() {
		// TODO ������������� ��������� �������� ������
		return super.getMaxFallHeight();
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment() {
		// TODO ������������� ��������� �������� ������
		return super.getHeldEquipment();
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		// TODO ������������� ��������� �������� ������
		return super.getArmorInventoryList();
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		// TODO ������������� ��������� �������� ������
		return super.getItemStackFromSlot(slotIn);
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		// TODO ������������� ��������� �������� ������
		super.setItemStackToSlot(slotIn, stack);
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		// TODO ������������� ��������� �������� ������
		super.dropEquipment(wasRecentlyHit, lootingModifier);
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO ������������� ��������� �������� ������
		super.setEquipmentBasedOnDifficulty(difficulty);
	}

	@Override
	protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO ������������� ��������� �������� ������
		super.setEnchantmentBasedOnDifficulty(difficulty);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		// TODO ������������� ��������� �������� ������
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	public boolean canBeSteered() {
		// TODO ������������� ��������� �������� ������
		return super.canBeSteered();
	}

	@Override
	public void enablePersistence() {
		// TODO ������������� ��������� �������� ������
		super.enablePersistence();
	}

	@Override
	public void setDropChance(EntityEquipmentSlot slotIn, float chance) {
		// TODO ������������� ��������� �������� ������
		super.setDropChance(slotIn, chance);
	}

	@Override
	public boolean canPickUpLoot() {
		// TODO ������������� ��������� �������� ������
		return super.canPickUpLoot();
	}

	@Override
	public void setCanPickUpLoot(boolean canPickup) {
		// TODO ������������� ��������� �������� ������
		super.setCanPickUpLoot(canPickup);
	}

	@Override
	public boolean isNoDespawnRequired() {
		// TODO ������������� ��������� �������� ������
		return super.isNoDespawnRequired();
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		// TODO ������������� ��������� �������� ������
		return super.processInteract(player, hand);
	}

	@Override
	public void clearLeashed(boolean sendPacket, boolean dropLead) {
		// TODO ������������� ��������� �������� ������
		super.clearLeashed(sendPacket, dropLead);
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		// TODO ������������� ��������� �������� ������
		return super.canBeLeashedTo(player);
	}

	@Override
	public boolean getLeashed() {
		// TODO ������������� ��������� �������� ������
		return super.getLeashed();
	}

	@Override
	public Entity getLeashHolder() {
		// TODO ������������� ��������� �������� ������
		return super.getLeashHolder();
	}

	@Override
	public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
		// TODO ������������� ��������� �������� ������
		super.setLeashHolder(entityIn, sendAttachNotification);
	}

	@Override
	public boolean startRiding(Entity entityIn, boolean force) {
		// TODO ������������� ��������� �������� ������
		return super.startRiding(entityIn, force);
	}

	@Override
	public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
		// TODO ������������� ��������� �������� ������
		return super.replaceItemInInventory(inventorySlot, itemStackIn);
	}

	@Override
	public boolean canPassengerSteer() {
		// TODO ������������� ��������� �������� ������
		return super.canPassengerSteer();
	}

	@Override
	public boolean isServerWorld() {
		// TODO ������������� ��������� �������� ������
		return super.isServerWorld();
	}

	@Override
	public void setNoAI(boolean disable) {
		// TODO ������������� ��������� �������� ������
		super.setNoAI(disable);
	}

	@Override
	public void setLeftHanded(boolean leftHanded) {
		// TODO ������������� ��������� �������� ������
		super.setLeftHanded(leftHanded);
	}

	@Override
	public boolean isAIDisabled() {
		// TODO ������������� ��������� �������� ������
		return super.isAIDisabled();
	}

	@Override
	public boolean isLeftHanded() {
		// TODO ������������� ��������� �������� ������
		return super.isLeftHanded();
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		// TODO ������������� ��������� �������� ������
		return super.getPrimaryHand();
	}

	@Override
	public void onKillCommand() {
		// TODO ������������� ��������� �������� ������
		super.onKillCommand();
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		super.updateFallState(y, onGroundIn, state, pos);
	}

	@Override
	public boolean canBreatheUnderwater() {
		// TODO ������������� ��������� �������� ������
		return super.canBreatheUnderwater();
	}

	@Override
	protected void frostWalk(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		super.frostWalk(pos);
	}

	@Override
	public boolean isChild() {
		// TODO ������������� ��������� �������� ������
		return super.isChild();
	}

	@Override
	protected void onDeathUpdate() {
		// TODO ������������� ��������� �������� ������
		super.onDeathUpdate();
	}

	@Override
	protected int decreaseAirSupply(int air) {
		// TODO ������������� ��������� �������� ������
		return super.decreaseAirSupply(air);
	}

	@Override
	protected boolean isPlayer() {
		// TODO ������������� ��������� �������� ������
		return super.isPlayer();
	}

	@Override
	public Random getRNG() {
		// TODO ������������� ��������� �������� ������
		return super.getRNG();
	}

	@Override
	public EntityLivingBase getRevengeTarget() {
		// TODO ������������� ��������� �������� ������
		return super.getRevengeTarget();
	}

	@Override
	public int getRevengeTimer() {
		// TODO ������������� ��������� �������� ������
		return super.getRevengeTimer();
	}

	@Override
	public void setRevengeTarget(EntityLivingBase livingBase) {
		// TODO ������������� ��������� �������� ������
		super.setRevengeTarget(livingBase);
	}

	@Override
	public EntityLivingBase getLastAttackedEntity() {
		// TODO ������������� ��������� �������� ������
		return super.getLastAttackedEntity();
	}

	@Override
	public int getLastAttackedEntityTime() {
		// TODO ������������� ��������� �������� ������
		return super.getLastAttackedEntityTime();
	}

	@Override
	public void setLastAttackedEntity(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.setLastAttackedEntity(entityIn);
	}

	@Override
	public int getIdleTime() {
		// TODO ������������� ��������� �������� ������
		return super.getIdleTime();
	}

	@Override
	protected void playEquipSound(ItemStack stack) {
		// TODO ������������� ��������� �������� ������
		super.playEquipSound(stack);
	}

	@Override
	protected void updatePotionEffects() {
		// TODO ������������� ��������� �������� ������
		super.updatePotionEffects();
	}

	@Override
	protected void updatePotionMetadata() {
		// TODO ������������� ��������� �������� ������
		super.updatePotionMetadata();
	}

	@Override
	protected void resetPotionEffectMetadata() {
		// TODO ������������� ��������� �������� ������
		super.resetPotionEffectMetadata();
	}

	@Override
	public void clearActivePotions() {
		// TODO ������������� ��������� �������� ������
		super.clearActivePotions();
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		// TODO ������������� ��������� �������� ������
		return super.getActivePotionEffects();
	}

	@Override
	public Map<Potion, PotionEffect> getActivePotionMap() {
		// TODO ������������� ��������� �������� ������
		return super.getActivePotionMap();
	}

	@Override
	public boolean isPotionActive(Potion potionIn) {
		// TODO ������������� ��������� �������� ������
		return super.isPotionActive(potionIn);
	}

	@Override
	public PotionEffect getActivePotionEffect(Potion potionIn) {
		// TODO ������������� ��������� �������� ������
		return super.getActivePotionEffect(potionIn);
	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		// TODO ������������� ��������� �������� ������
		super.addPotionEffect(potioneffectIn);
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		// TODO ������������� ��������� �������� ������
		return super.isPotionApplicable(potioneffectIn);
	}

	@Override
	public boolean isEntityUndead() {
		// TODO ������������� ��������� �������� ������
		return super.isEntityUndead();
	}

	@Override
	public PotionEffect removeActivePotionEffect(Potion potioneffectin) {
		// TODO ������������� ��������� �������� ������
		return super.removeActivePotionEffect(potioneffectin);
	}

	@Override
	public void removePotionEffect(Potion potionIn) {
		// TODO ������������� ��������� �������� ������
		super.removePotionEffect(potionIn);
	}

	@Override
	protected void onNewPotionEffect(PotionEffect id) {
		// TODO ������������� ��������� �������� ������
		super.onNewPotionEffect(id);
	}

	@Override
	protected void onChangedPotionEffect(PotionEffect id, boolean p_70695_2_) {
		// TODO ������������� ��������� �������� ������
		super.onChangedPotionEffect(id, p_70695_2_);
	}

	@Override
	protected void onFinishedPotionEffect(PotionEffect effect) {
		// TODO ������������� ��������� �������� ������
		super.onFinishedPotionEffect(effect);
	}

	@Override
	public void heal(float healAmount) {
		// TODO ������������� ��������� �������� ������
		super.heal(healAmount);
	}

	@Override
	public void setHealth(float health) {
		// TODO ������������� ��������� �������� ������
		super.setHealth(health);
	}

	@Override
	protected void blockUsingShield(EntityLivingBase p_190629_1_) {
		// TODO ������������� ��������� �������� ������
		super.blockUsingShield(p_190629_1_);
	}

	@Override
	public DamageSource getLastDamageSource() {
		// TODO ������������� ��������� �������� ������
		return super.getLastDamageSource();
	}

	@Override
	public void renderBrokenItemStack(ItemStack stack) {
		// TODO ������������� ��������� �������� ������
		super.renderBrokenItemStack(stack);
	}

	@Override
	public void onDeath(DamageSource cause) {
		// TODO ������������� ��������� �������� ������
		super.onDeath(cause);
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		// TODO ������������� ��������� �������� ������
		super.knockBack(entityIn, strength, xRatio, zRatio);
	}

	@Override
	public boolean isOnLadder() {
		// TODO ������������� ��������� �������� ������
		return super.isOnLadder();
	}

	@Override
	public boolean isEntityAlive() {
		// TODO ������������� ��������� �������� ������
		return super.isEntityAlive();
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		// TODO ������������� ��������� �������� ������
		super.fall(distance, damageMultiplier);
	}

	@Override
	public void performHurtAnimation() {
		// TODO ������������� ��������� �������� ������
		super.performHurtAnimation();
	}

	@Override
	public int getTotalArmorValue() {
		// TODO ������������� ��������� �������� ������
		return super.getTotalArmorValue();
	}

	@Override
	protected void damageArmor(float damage) {
		// TODO ������������� ��������� �������� ������
		super.damageArmor(damage);
	}

	@Override
	protected void damageShield(float damage) {
		// TODO ������������� ��������� �������� ������
		super.damageShield(damage);
	}

	@Override
	protected float applyArmorCalculations(DamageSource source, float damage) {
		// TODO ������������� ��������� �������� ������
		return super.applyArmorCalculations(source, damage);
	}

	@Override
	protected float applyPotionDamageCalculations(DamageSource source, float damage) {
		// TODO ������������� ��������� �������� ������
		return super.applyPotionDamageCalculations(source, damage);
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		// TODO ������������� ��������� �������� ������
		super.damageEntity(damageSrc, damageAmount);
	}

	@Override
	public CombatTracker getCombatTracker() {
		// TODO ������������� ��������� �������� ������
		return super.getCombatTracker();
	}

	@Override
	public EntityLivingBase getAttackingEntity() {
		// TODO ������������� ��������� �������� ������
		return super.getAttackingEntity();
	}

	@Override
	public void swingArm(EnumHand hand) {
		// TODO ������������� ��������� �������� ������
		super.swingArm(hand);
	}

	@Override
	protected void outOfWorld() {
		// TODO ������������� ��������� �������� ������
		super.outOfWorld();
	}

	@Override
	protected void updateArmSwingProgress() {
		// TODO ������������� ��������� �������� ������
		super.updateArmSwingProgress();
	}

	@Override
	public IAttributeInstance getEntityAttribute(IAttribute attribute) {
		// TODO ������������� ��������� �������� ������
		return super.getEntityAttribute(attribute);
	}

	@Override
	public AbstractAttributeMap getAttributeMap() {
		// TODO ������������� ��������� �������� ������
		return super.getAttributeMap();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		// TODO ������������� ��������� �������� ������
		return super.getCreatureAttribute();
	}

	@Override
	public ItemStack getHeldItemMainhand() {
		// TODO ������������� ��������� �������� ������
		return super.getHeldItemMainhand();
	}

	@Override
	public ItemStack getHeldItemOffhand() {
		// TODO ������������� ��������� �������� ������
		return super.getHeldItemOffhand();
	}

	@Override
	public ItemStack getHeldItem(EnumHand hand) {
		// TODO ������������� ��������� �������� ������
		return super.getHeldItem(hand);
	}

	@Override
	public void setHeldItem(EnumHand hand, ItemStack stack) {
		// TODO ������������� ��������� �������� ������
		super.setHeldItem(hand, stack);
	}

	@Override
	public boolean hasItemInSlot(EntityEquipmentSlot p_190630_1_) {
		// TODO ������������� ��������� �������� ������
		return super.hasItemInSlot(p_190630_1_);
	}

	@Override
	public void setSprinting(boolean sprinting) {
		// TODO ������������� ��������� �������� ������
		super.setSprinting(sprinting);
	}

	@Override
	protected float getSoundVolume() {
		// TODO ������������� ��������� �������� ������
		return super.getSoundVolume();
	}

	@Override
	protected float getSoundPitch() {
		// TODO ������������� ��������� �������� ������
		return super.getSoundPitch();
	}

	@Override
	protected boolean isMovementBlocked() {
		// TODO ������������� ��������� �������� ������
		return super.isMovementBlocked();
	}

	@Override
	public void dismountEntity(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.dismountEntity(entityIn);
	}

	@Override
	public boolean getAlwaysRenderNameTagForRender() {
		// TODO ������������� ��������� �������� ������
		return super.getAlwaysRenderNameTagForRender();
	}

	@Override
	protected float getJumpUpwardsMotion() {
		// TODO ������������� ��������� �������� ������
		return super.getJumpUpwardsMotion();
	}

	@Override
	protected void jump() {
		// TODO ������������� ��������� �������� ������
		super.jump();
	}

	@Override
	protected void handleJumpWater() {
		// TODO ������������� ��������� �������� ������
		super.handleJumpWater();
	}

	@Override
	protected void handleJumpLava() {
		// TODO ������������� ��������� �������� ������
		super.handleJumpLava();
	}

	@Override
	protected float getWaterSlowDown() {
		// TODO ������������� ��������� �������� ������
		return super.getWaterSlowDown();
	}

	@Override
	public void travel(float strafe, float vertical, float forward) {
		// TODO ������������� ��������� �������� ������
		super.travel(strafe, vertical, forward);
	}

	@Override
	public float getAIMoveSpeed() {
		// TODO ������������� ��������� �������� ������
		return super.getAIMoveSpeed();
	}

	@Override
	public boolean isPlayerSleeping() {
		// TODO ������������� ��������� �������� ������
		return super.isPlayerSleeping();
	}

	@Override
	protected void collideWithNearbyEntities() {
		// TODO ������������� ��������� �������� ������
		super.collideWithNearbyEntities();
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.collideWithEntity(entityIn);
	}

	@Override
	public void dismountRidingEntity() {
		// TODO ������������� ��������� �������� ������
		super.dismountRidingEntity();
	}

	@Override
	public void updateRidden() {
		// TODO ������������� ��������� �������� ������
		super.updateRidden();
	}

	@Override
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		// TODO ������������� ��������� �������� ������
		super.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
	}

	@Override
	public void setJumping(boolean jumping) {
		// TODO ������������� ��������� �������� ������
		super.setJumping(jumping);
	}

	@Override
	public void onItemPickup(Entity entityIn, int quantity) {
		// TODO ������������� ��������� �������� ������
		super.onItemPickup(entityIn, quantity);
	}

	@Override
	public boolean canEntityBeSeen(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.canEntityBeSeen(entityIn);
	}

	@Override
	public Vec3d getLook(float partialTicks) {
		// TODO ������������� ��������� �������� ������
		return super.getLook(partialTicks);
	}

	@Override
	public float getSwingProgress(float partialTickTime) {
		// TODO ������������� ��������� �������� ������
		return super.getSwingProgress(partialTickTime);
	}

	@Override
	public boolean canBeCollidedWith() {
		// TODO ������������� ��������� �������� ������
		return super.canBeCollidedWith();
	}

	@Override
	public boolean canBePushed() {
		// TODO ������������� ��������� �������� ������
		return super.canBePushed();
	}

	@Override
	protected void markVelocityChanged() {
		// TODO ������������� ��������� �������� ������
		super.markVelocityChanged();
	}

	@Override
	public float getRotationYawHead() {
		// TODO ������������� ��������� �������� ������
		return super.getRotationYawHead();
	}

	@Override
	public void setRotationYawHead(float rotation) {
		// TODO ������������� ��������� �������� ������
		super.setRotationYawHead(rotation);
	}

	@Override
	public void setRenderYawOffset(float offset) {
		// TODO ������������� ��������� �������� ������
		super.setRenderYawOffset(offset);
	}

	@Override
	public float getAbsorptionAmount() {
		// TODO ������������� ��������� �������� ������
		return super.getAbsorptionAmount();
	}

	@Override
	public void setAbsorptionAmount(float amount) {
		// TODO ������������� ��������� �������� ������
		super.setAbsorptionAmount(amount);
	}

	@Override
	public void sendEnterCombat() {
		// TODO ������������� ��������� �������� ������
		super.sendEnterCombat();
	}

	@Override
	public void sendEndCombat() {
		// TODO ������������� ��������� �������� ������
		super.sendEndCombat();
	}

	@Override
	protected void markPotionsDirty() {
		// TODO ������������� ��������� �������� ������
		super.markPotionsDirty();
	}

	@Override
	public void curePotionEffects(ItemStack curativeItem) {
		// TODO ������������� ��������� �������� ������
		super.curePotionEffects(curativeItem);
	}

	@Override
	public boolean shouldRiderFaceForward(EntityPlayer player) {
		// TODO ������������� ��������� �������� ������
		return super.shouldRiderFaceForward(player);
	}

	@Override
	public boolean isHandActive() {
		// TODO ������������� ��������� �������� ������
		return super.isHandActive();
	}

	@Override
	public EnumHand getActiveHand() {
		// TODO ������������� ��������� �������� ������
		return super.getActiveHand();
	}

	@Override
	protected void updateActiveHand() {
		// TODO ������������� ��������� �������� ������
		super.updateActiveHand();
	}

	@Override
	public void setActiveHand(EnumHand hand) {
		// TODO ������������� ��������� �������� ������
		super.setActiveHand(hand);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		// TODO ������������� ��������� �������� ������
		super.notifyDataManagerChange(key);
	}

	@Override
	protected void updateItemUse(ItemStack stack, int eatingParticleCount) {
		// TODO ������������� ��������� �������� ������
		super.updateItemUse(stack, eatingParticleCount);
	}

	@Override
	protected void onItemUseFinish() {
		// TODO ������������� ��������� �������� ������
		super.onItemUseFinish();
	}

	@Override
	public ItemStack getActiveItemStack() {
		// TODO ������������� ��������� �������� ������
		return super.getActiveItemStack();
	}

	@Override
	public int getItemInUseCount() {
		// TODO ������������� ��������� �������� ������
		return super.getItemInUseCount();
	}

	@Override
	public int getItemInUseMaxCount() {
		// TODO ������������� ��������� �������� ������
		return super.getItemInUseMaxCount();
	}

	@Override
	public void stopActiveHand() {
		// TODO ������������� ��������� �������� ������
		super.stopActiveHand();
	}

	@Override
	public void resetActiveHand() {
		// TODO ������������� ��������� �������� ������
		super.resetActiveHand();
	}

	@Override
	public boolean isActiveItemStackBlocking() {
		// TODO ������������� ��������� �������� ������
		return super.isActiveItemStackBlocking();
	}

	@Override
	public boolean isElytraFlying() {
		// TODO ������������� ��������� �������� ������
		return super.isElytraFlying();
	}

	@Override
	public int getTicksElytraFlying() {
		// TODO ������������� ��������� �������� ������
		return super.getTicksElytraFlying();
	}

	@Override
	public boolean attemptTeleport(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.attemptTeleport(x, y, z);
	}

	@Override
	public boolean canBeHitWithPotion() {
		// TODO ������������� ��������� �������� ������
		return super.canBeHitWithPotion();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO ������������� ��������� �������� ������
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO ������������� ��������� �������� ������
		return super.hasCapability(capability, facing);
	}

	@Override
	public boolean attackable() {
		// TODO ������������� ��������� �������� ������
		return super.attackable();
	}

	@Override
	public void setPartying(BlockPos pos, boolean p_191987_2_) {
		// TODO ������������� ��������� �������� ������
		super.setPartying(pos, p_191987_2_);
	}

	@Override
	public int getEntityId() {
		// TODO ������������� ��������� �������� ������
		return super.getEntityId();
	}

	@Override
	public void setEntityId(int id) {
		// TODO ������������� ��������� �������� ������
		super.setEntityId(id);
	}

	@Override
	public Set<String> getTags() {
		// TODO ������������� ��������� �������� ������
		return super.getTags();
	}

	@Override
	public boolean addTag(String tag) {
		// TODO ������������� ��������� �������� ������
		return super.addTag(tag);
	}

	@Override
	public boolean removeTag(String tag) {
		// TODO ������������� ��������� �������� ������
		return super.removeTag(tag);
	}

	@Override
	public EntityDataManager getDataManager() {
		// TODO ������������� ��������� �������� ������
		return super.getDataManager();
	}

	@Override
	public boolean equals(Object p_equals_1_) {
		// TODO ������������� ��������� �������� ������
		return super.equals(p_equals_1_);
	}

	@Override
	public int hashCode() {
		// TODO ������������� ��������� �������� ������
		return super.hashCode();
	}

	@Override
	protected void preparePlayerToSpawn() {
		// TODO ������������� ��������� �������� ������
		super.preparePlayerToSpawn();
	}

	@Override
	public void setDead() {
		// TODO ������������� ��������� �������� ������
		super.setDead();
	}

	@Override
	public void setDropItemsWhenDead(boolean dropWhenDead) {
		// TODO ������������� ��������� �������� ������
		super.setDropItemsWhenDead(dropWhenDead);
	}

	@Override
	protected void setSize(float width, float height) {
		// TODO ������������� ��������� �������� ������
		super.setSize(width, height);
	}

	@Override
	protected void setRotation(float yaw, float pitch) {
		// TODO ������������� ��������� �������� ������
		super.setRotation(yaw, pitch);
	}

	@Override
	public void setPosition(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		super.setPosition(x, y, z);
	}

	@Override
	public void turn(float yaw, float pitch) {
		// TODO ������������� ��������� �������� ������
		super.turn(yaw, pitch);
	}

	@Override
	protected void decrementTimeUntilPortal() {
		// TODO ������������� ��������� �������� ������
		super.decrementTimeUntilPortal();
	}

	@Override
	public int getMaxInPortalTime() {
		// TODO ������������� ��������� �������� ������
		return super.getMaxInPortalTime();
	}

	@Override
	protected void setOnFireFromLava() {
		// TODO ������������� ��������� �������� ������
		super.setOnFireFromLava();
	}

	@Override
	public void setFire(int seconds) {
		// TODO ������������� ��������� �������� ������
		super.setFire(seconds);
	}

	@Override
	public void extinguish() {
		// TODO ������������� ��������� �������� ������
		super.extinguish();
	}

	@Override
	public boolean isOffsetPositionInLiquid(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.isOffsetPositionInLiquid(x, y, z);
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		super.move(type, x, y, z);
	}

	@Override
	public void resetPositionToBB() {
		// TODO ������������� ��������� �������� ������
		super.resetPositionToBB();
	}

	@Override
	protected void doBlockCollisions() {
		// TODO ������������� ��������� �������� ������
		super.doBlockCollisions();
	}

	@Override
	protected void onInsideBlock(IBlockState p_191955_1_) {
		// TODO ������������� ��������� �������� ������
		super.onInsideBlock(p_191955_1_);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		// TODO ������������� ��������� �������� ������
		super.playStepSound(pos, blockIn);
	}

	@Override
	protected float playFlySound(float p_191954_1_) {
		// TODO ������������� ��������� �������� ������
		return super.playFlySound(p_191954_1_);
	}

	@Override
	protected boolean makeFlySound() {
		// TODO ������������� ��������� �������� ������
		return super.makeFlySound();
	}

	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch) {
		// TODO ������������� ��������� �������� ������
		super.playSound(soundIn, volume, pitch);
	}

	@Override
	public boolean isSilent() {
		// TODO ������������� ��������� �������� ������
		return super.isSilent();
	}

	@Override
	public void setSilent(boolean isSilent) {
		// TODO ������������� ��������� �������� ������
		super.setSilent(isSilent);
	}

	@Override
	public boolean hasNoGravity() {
		// TODO ������������� ��������� �������� ������
		return super.hasNoGravity();
	}

	@Override
	public void setNoGravity(boolean noGravity) {
		// TODO ������������� ��������� �������� ������
		super.setNoGravity(noGravity);
	}

	@Override
	protected boolean canTriggerWalking() {
		// TODO ������������� ��������� �������� ������
		return super.canTriggerWalking();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		// TODO ������������� ��������� �������� ������
		return super.getCollisionBoundingBox();
	}

	@Override
	protected void dealFireDamage(int amount) {
		// TODO ������������� ��������� �������� ������
		super.dealFireDamage(amount);
	}

	@Override
	public boolean isWet() {
		// TODO ������������� ��������� �������� ������
		return super.isWet();
	}

	@Override
	public boolean isInWater() {
		// TODO ������������� ��������� �������� ������
		return super.isInWater();
	}

	@Override
	public boolean isOverWater() {
		// TODO ������������� ��������� �������� ������
		return super.isOverWater();
	}

	@Override
	public boolean handleWaterMovement() {
		// TODO ������������� ��������� �������� ������
		return super.handleWaterMovement();
	}

	@Override
	protected void doWaterSplashEffect() {
		// TODO ������������� ��������� �������� ������
		super.doWaterSplashEffect();
	}

	@Override
	public void spawnRunningParticles() {
		// TODO ������������� ��������� �������� ������
		super.spawnRunningParticles();
	}

	@Override
	protected void createRunningParticles() {
		// TODO ������������� ��������� �������� ������
		super.createRunningParticles();
	}

	@Override
	public boolean isInsideOfMaterial(Material materialIn) {
		// TODO ������������� ��������� �������� ������
		return super.isInsideOfMaterial(materialIn);
	}

	@Override
	public boolean isInLava() {
		// TODO ������������� ��������� �������� ������
		return super.isInLava();
	}

	@Override
	public void moveRelative(float strafe, float up, float forward, float friction) {
		// TODO ������������� ��������� �������� ������
		super.moveRelative(strafe, up, forward, friction);
	}

	@Override
	public int getBrightnessForRender() {
		// TODO ������������� ��������� �������� ������
		return super.getBrightnessForRender();
	}

	@Override
	public float getBrightness() {
		// TODO ������������� ��������� �������� ������
		return super.getBrightness();
	}

	@Override
	public void setWorld(World worldIn) {
		// TODO ������������� ��������� �������� ������
		super.setWorld(worldIn);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
		// TODO ������������� ��������� �������� ������
		super.setPositionAndRotation(x, y, z, yaw, pitch);
	}

	@Override
	public void moveToBlockPosAndAngles(BlockPos pos, float rotationYawIn, float rotationPitchIn) {
		// TODO ������������� ��������� �������� ������
		super.moveToBlockPosAndAngles(pos, rotationYawIn, rotationPitchIn);
	}

	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
		// TODO ������������� ��������� �������� ������
		super.setLocationAndAngles(x, y, z, yaw, pitch);
	}

	@Override
	public float getDistance(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.getDistance(entityIn);
	}

	@Override
	public double getDistanceSq(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.getDistanceSq(x, y, z);
	}

	@Override
	public double getDistanceSq(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		return super.getDistanceSq(pos);
	}

	@Override
	public double getDistanceSqToCenter(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		return super.getDistanceSqToCenter(pos);
	}

	@Override
	public double getDistance(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.getDistance(x, y, z);
	}

	@Override
	public double getDistanceSq(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.getDistanceSq(entityIn);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		// TODO ������������� ��������� �������� ������
		super.onCollideWithPlayer(entityIn);
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.applyEntityCollision(entityIn);
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		super.addVelocity(x, y, z);
	}

	@Override
	public Vec3d getPositionEyes(float partialTicks) {
		// TODO ������������� ��������� �������� ������
		return super.getPositionEyes(partialTicks);
	}

	@Override
	public RayTraceResult rayTrace(double blockReachDistance, float partialTicks) {
		// TODO ������������� ��������� �������� ������
		return super.rayTrace(blockReachDistance, partialTicks);
	}

	@Override
	public void awardKillScore(Entity p_191956_1_, int p_191956_2_, DamageSource p_191956_3_) {
		// TODO ������������� ��������� �������� ������
		super.awardKillScore(p_191956_1_, p_191956_2_, p_191956_3_);
	}

	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.isInRangeToRender3d(x, y, z);
	}

	@Override
	public boolean isInRangeToRenderDist(double distance) {
		// TODO ������������� ��������� �������� ������
		return super.isInRangeToRenderDist(distance);
	}

	@Override
	public boolean writeToNBTAtomically(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		return super.writeToNBTAtomically(compound);
	}

	@Override
	public boolean writeToNBTOptional(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		return super.writeToNBTOptional(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO ������������� ��������� �������� ������
		super.readFromNBT(compound);
	}

	@Override
	protected boolean shouldSetPosAfterLoading() {
		// TODO ������������� ��������� �������� ������
		return super.shouldSetPosAfterLoading();
	}

	@Override
	protected NBTTagList newDoubleNBTList(double... numbers) {
		// TODO ������������� ��������� �������� ������
		return super.newDoubleNBTList(numbers);
	}

	@Override
	protected NBTTagList newFloatNBTList(float... numbers) {
		// TODO ������������� ��������� �������� ������
		return super.newFloatNBTList(numbers);
	}

	@Override
	public EntityItem dropItem(Item itemIn, int size) {
		// TODO ������������� ��������� �������� ������
		return super.dropItem(itemIn, size);
	}

	@Override
	public EntityItem dropItemWithOffset(Item itemIn, int size, float offsetY) {
		// TODO ������������� ��������� �������� ������
		return super.dropItemWithOffset(itemIn, size, offsetY);
	}

	@Override
	public EntityItem entityDropItem(ItemStack stack, float offsetY) {
		// TODO ������������� ��������� �������� ������
		return super.entityDropItem(stack, offsetY);
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		// TODO ������������� ��������� �������� ������
		return super.isEntityInsideOpaqueBlock();
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.getCollisionBox(entityIn);
	}

	@Override
	public void updatePassenger(Entity passenger) {
		// TODO ������������� ��������� �������� ������
		super.updatePassenger(passenger);
	}

	@Override
	public void applyOrientationToEntity(Entity entityToUpdate) {
		// TODO ������������� ��������� �������� ������
		super.applyOrientationToEntity(entityToUpdate);
	}

	@Override
	public double getYOffset() {
		// TODO ������������� ��������� �������� ������
		return super.getYOffset();
	}

	@Override
	public double getMountedYOffset() {
		// TODO ������������� ��������� �������� ������
		return super.getMountedYOffset();
	}

	@Override
	public boolean startRiding(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.startRiding(entityIn);
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.canBeRidden(entityIn);
	}

	@Override
	public void removePassengers() {
		// TODO ������������� ��������� �������� ������
		super.removePassengers();
	}

	@Override
	protected void addPassenger(Entity passenger) {
		// TODO ������������� ��������� �������� ������
		super.addPassenger(passenger);
	}

	@Override
	protected void removePassenger(Entity passenger) {
		// TODO ������������� ��������� �������� ������
		super.removePassenger(passenger);
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		// TODO ������������� ��������� �������� ������
		return super.canFitPassenger(passenger);
	}

	@Override
	public float getCollisionBorderSize() {
		// TODO ������������� ��������� �������� ������
		return super.getCollisionBorderSize();
	}

	@Override
	public Vec3d getLookVec() {
		// TODO ������������� ��������� �������� ������
		return super.getLookVec();
	}

	@Override
	public Vec2f getPitchYaw() {
		// TODO ������������� ��������� �������� ������
		return super.getPitchYaw();
	}

	@Override
	public Vec3d getForward() {
		// TODO ������������� ��������� �������� ������
		return super.getForward();
	}

	@Override
	public void setPortal(BlockPos pos) {
		// TODO ������������� ��������� �������� ������
		super.setPortal(pos);
	}

	@Override
	public int getPortalCooldown() {
		// TODO ������������� ��������� �������� ������
		return super.getPortalCooldown();
	}

	@Override
	public void setVelocity(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		super.setVelocity(x, y, z);
	}

	@Override
	public Iterable<ItemStack> getEquipmentAndArmor() {
		// TODO ������������� ��������� �������� ������
		return super.getEquipmentAndArmor();
	}

	@Override
	public boolean isBurning() {
		// TODO ������������� ��������� �������� ������
		return super.isBurning();
	}

	@Override
	public boolean isRiding() {
		// TODO ������������� ��������� �������� ������
		return super.isRiding();
	}

	@Override
	public boolean isBeingRidden() {
		// TODO ������������� ��������� �������� ������
		return super.isBeingRidden();
	}

	@Override
	public boolean isSneaking() {
		// TODO ������������� ��������� �������� ������
		return super.isSneaking();
	}

	@Override
	public void setSneaking(boolean sneaking) {
		// TODO ������������� ��������� �������� ������
		super.setSneaking(sneaking);
	}

	@Override
	public boolean isSprinting() {
		// TODO ������������� ��������� �������� ������
		return super.isSprinting();
	}

	@Override
	public boolean isGlowing() {
		// TODO ������������� ��������� �������� ������
		return super.isGlowing();
	}

	@Override
	public void setGlowing(boolean glowingIn) {
		// TODO ������������� ��������� �������� ������
		super.setGlowing(glowingIn);
	}

	@Override
	public boolean isInvisible() {
		// TODO ������������� ��������� �������� ������
		return super.isInvisible();
	}

	@Override
	public boolean isInvisibleToPlayer(EntityPlayer player) {
		// TODO ������������� ��������� �������� ������
		return super.isInvisibleToPlayer(player);
	}

	@Override
	public Team getTeam() {
		// TODO ������������� ��������� �������� ������
		return super.getTeam();
	}

	@Override
	public boolean isOnSameTeam(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.isOnSameTeam(entityIn);
	}

	@Override
	public boolean isOnScoreboardTeam(Team teamIn) {
		// TODO ������������� ��������� �������� ������
		return super.isOnScoreboardTeam(teamIn);
	}

	@Override
	public void setInvisible(boolean invisible) {
		// TODO ������������� ��������� �������� ������
		super.setInvisible(invisible);
	}

	@Override
	protected boolean getFlag(int flag) {
		// TODO ������������� ��������� �������� ������
		return super.getFlag(flag);
	}

	@Override
	protected void setFlag(int flag, boolean set) {
		// TODO ������������� ��������� �������� ������
		super.setFlag(flag, set);
	}

	@Override
	public int getAir() {
		// TODO ������������� ��������� �������� ������
		return super.getAir();
	}

	@Override
	public void setAir(int air) {
		// TODO ������������� ��������� �������� ������
		super.setAir(air);
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		// TODO ������������� ��������� �������� ������
		super.onStruckByLightning(lightningBolt);
	}

	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn) {
		// TODO ������������� ��������� �������� ������
		super.onKillEntity(entityLivingIn);
	}

	@Override
	protected boolean pushOutOfBlocks(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		return super.pushOutOfBlocks(x, y, z);
	}

	@Override
	public void setInWeb() {
		// TODO ������������� ��������� �������� ������
		super.setInWeb();
	}

	@Override
	public String getName() {
		// TODO ������������� ��������� �������� ������
		return super.getName();
	}

	@Override
	public Entity[] getParts() {
		// TODO ������������� ��������� �������� ������
		return super.getParts();
	}

	@Override
	public boolean isEntityEqual(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.isEntityEqual(entityIn);
	}

	@Override
	public boolean canBeAttackedWithItem() {
		// TODO ������������� ��������� �������� ������
		return super.canBeAttackedWithItem();
	}

	@Override
	public boolean hitByEntity(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.hitByEntity(entityIn);
	}

	@Override
	public String toString() {
		// TODO ������������� ��������� �������� ������
		return super.toString();
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		// TODO ������������� ��������� �������� ������
		return super.isEntityInvulnerable(source);
	}

	@Override
	public boolean getIsInvulnerable() {
		// TODO ������������� ��������� �������� ������
		return super.getIsInvulnerable();
	}

	@Override
	public void setEntityInvulnerable(boolean isInvulnerable) {
		// TODO ������������� ��������� �������� ������
		super.setEntityInvulnerable(isInvulnerable);
	}

	@Override
	public void copyLocationAndAnglesFrom(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.copyLocationAndAnglesFrom(entityIn);
	}

	@Override
	public Entity changeDimension(int dimensionIn) {
		// TODO ������������� ��������� �������� ������
		return super.changeDimension(dimensionIn);
	}

	@Override
	public Entity changeDimension(int dimensionIn, ITeleporter teleporter) {
		// TODO ������������� ��������� �������� ������
		return super.changeDimension(dimensionIn, teleporter);
	}

	@Override
	public boolean isNonBoss() {
		// TODO ������������� ��������� �������� ������
		return super.isNonBoss();
	}

	@Override
	public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn) {
		// TODO ������������� ��������� �������� ������
		return super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
	}

	@Override
	public boolean canExplosionDestroyBlock(Explosion explosionIn, World worldIn, BlockPos pos,
			IBlockState blockStateIn, float p_174816_5_) {
		// TODO ������������� ��������� �������� ������
		return super.canExplosionDestroyBlock(explosionIn, worldIn, pos, blockStateIn, p_174816_5_);
	}

	@Override
	public Vec3d getLastPortalVec() {
		// TODO ������������� ��������� �������� ������
		return super.getLastPortalVec();
	}

	@Override
	public EnumFacing getTeleportDirection() {
		// TODO ������������� ��������� �������� ������
		return super.getTeleportDirection();
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		// TODO ������������� ��������� �������� ������
		return super.doesEntityNotTriggerPressurePlate();
	}

	@Override
	public void addEntityCrashInfo(CrashReportCategory category) {
		// TODO ������������� ��������� �������� ������
		super.addEntityCrashInfo(category);
	}

	@Override
	public void setUniqueId(UUID uniqueIdIn) {
		// TODO ������������� ��������� �������� ������
		super.setUniqueId(uniqueIdIn);
	}

	@Override
	public boolean canRenderOnFire() {
		// TODO ������������� ��������� �������� ������
		return super.canRenderOnFire();
	}

	@Override
	public UUID getUniqueID() {
		// TODO ������������� ��������� �������� ������
		return super.getUniqueID();
	}

	@Override
	public String getCachedUniqueIdString() {
		// TODO ������������� ��������� �������� ������
		return super.getCachedUniqueIdString();
	}

	@Override
	public boolean isPushedByWater() {
		// TODO ������������� ��������� �������� ������
		return super.isPushedByWater();
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO ������������� ��������� �������� ������
		return super.getDisplayName();
	}

	@Override
	public void setCustomNameTag(String name) {
		// TODO ������������� ��������� �������� ������
		super.setCustomNameTag(name);
	}

	@Override
	public String getCustomNameTag() {
		// TODO ������������� ��������� �������� ������
		return super.getCustomNameTag();
	}

	@Override
	public boolean hasCustomName() {
		// TODO ������������� ��������� �������� ������
		return super.hasCustomName();
	}

	@Override
	public void setAlwaysRenderNameTag(boolean alwaysRenderNameTag) {
		// TODO ������������� ��������� �������� ������
		super.setAlwaysRenderNameTag(alwaysRenderNameTag);
	}

	@Override
	public boolean getAlwaysRenderNameTag() {
		// TODO ������������� ��������� �������� ������
		return super.getAlwaysRenderNameTag();
	}

	@Override
	public void setPositionAndUpdate(double x, double y, double z) {
		// TODO ������������� ��������� �������� ������
		super.setPositionAndUpdate(x, y, z);
	}

	@Override
	public EnumFacing getHorizontalFacing() {
		// TODO ������������� ��������� �������� ������
		return super.getHorizontalFacing();
	}

	@Override
	public EnumFacing getAdjustedHorizontalFacing() {
		// TODO ������������� ��������� �������� ������
		return super.getAdjustedHorizontalFacing();
	}

	@Override
	protected HoverEvent getHoverEvent() {
		// TODO ������������� ��������� �������� ������
		return super.getHoverEvent();
	}

	@Override
	public boolean isSpectatedByPlayer(EntityPlayerMP player) {
		// TODO ������������� ��������� �������� ������
		return super.isSpectatedByPlayer(player);
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		// TODO ������������� ��������� �������� ������
		return super.getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		// TODO ������������� ��������� �������� ������
		return super.getRenderBoundingBox();
	}

	@Override
	public void setEntityBoundingBox(AxisAlignedBB bb) {
		// TODO ������������� ��������� �������� ������
		super.setEntityBoundingBox(bb);
	}

	@Override
	public float getEyeHeight() {
		// TODO ������������� ��������� �������� ������
		return super.getEyeHeight();
	}

	@Override
	public boolean isOutsideBorder() {
		// TODO ������������� ��������� �������� ������
		return super.isOutsideBorder();
	}

	@Override
	public void setOutsideBorder(boolean outsideBorder) {
		// TODO ������������� ��������� �������� ������
		super.setOutsideBorder(outsideBorder);
	}

	@Override
	public void sendMessage(ITextComponent component) {
		// TODO ������������� ��������� �������� ������
		super.sendMessage(component);
	}

	@Override
	public boolean canUseCommand(int permLevel, String commandName) {
		// TODO ������������� ��������� �������� ������
		return super.canUseCommand(permLevel, commandName);
	}

	@Override
	public BlockPos getPosition() {
		// TODO ������������� ��������� �������� ������
		return super.getPosition();
	}

	@Override
	public Vec3d getPositionVector() {
		// TODO ������������� ��������� �������� ������
		return super.getPositionVector();
	}

	@Override
	public World getEntityWorld() {
		// TODO ������������� ��������� �������� ������
		return super.getEntityWorld();
	}

	@Override
	public Entity getCommandSenderEntity() {
		// TODO ������������� ��������� �������� ������
		return super.getCommandSenderEntity();
	}

	@Override
	public boolean sendCommandFeedback() {
		// TODO ������������� ��������� �������� ������
		return super.sendCommandFeedback();
	}

	@Override
	public void setCommandStat(Type type, int amount) {
		// TODO ������������� ��������� �������� ������
		super.setCommandStat(type, amount);
	}

	@Override
	public MinecraftServer getServer() {
		// TODO ������������� ��������� �������� ������
		return super.getServer();
	}

	@Override
	public CommandResultStats getCommandStats() {
		// TODO ������������� ��������� �������� ������
		return super.getCommandStats();
	}

	@Override
	public void setCommandStats(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.setCommandStats(entityIn);
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		// TODO ������������� ��������� �������� ������
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public boolean isImmuneToExplosions() {
		// TODO ������������� ��������� �������� ������
		return super.isImmuneToExplosions();
	}

	@Override
	protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		super.applyEnchantments(entityLivingBaseIn, entityIn);
	}

	@Override
	public NBTTagCompound getEntityData() {
		// TODO ������������� ��������� �������� ������
		return super.getEntityData();
	}

	@Override
	public boolean shouldRiderSit() {
		// TODO ������������� ��������� �������� ������
		return super.shouldRiderSit();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		// TODO ������������� ��������� �������� ������
		return super.getPickedResult(target);
	}

	@Override
	public UUID getPersistentID() {
		// TODO ������������� ��������� �������� ������
		return super.getPersistentID();
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		// TODO ������������� ��������� �������� ������
		return super.shouldRenderInPass(pass);
	}

	@Override
	public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
		// TODO ������������� ��������� �������� ������
		return super.isCreatureType(type, forSpawnCount);
	}

	@Override
	public boolean canRiderInteract() {
		// TODO ������������� ��������� �������� ������
		return super.canRiderInteract();
	}

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		// TODO ������������� ��������� �������� ������
		return super.shouldDismountInWater(rider);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		// TODO ������������� ��������� �������� ������
		super.deserializeNBT(nbt);
	}

	@Override
	public NBTTagCompound serializeNBT() {
		// TODO ������������� ��������� �������� ������
		return super.serializeNBT();
	}

	@Override
	public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance) {
		// TODO ������������� ��������� �������� ������
		return super.canTrample(world, block, pos, fallDistance);
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		// TODO ������������� ��������� �������� ������
		super.addTrackingPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		// TODO ������������� ��������� �������� ������
		super.removeTrackingPlayer(player);
	}

	@Override
	public float getRotatedYaw(Rotation transformRotation) {
		// TODO ������������� ��������� �������� ������
		return super.getRotatedYaw(transformRotation);
	}

	@Override
	public float getMirroredYaw(Mirror transformMirror) {
		// TODO ������������� ��������� �������� ������
		return super.getMirroredYaw(transformMirror);
	}

	@Override
	public boolean ignoreItemEntityData() {
		// TODO ������������� ��������� �������� ������
		return super.ignoreItemEntityData();
	}

	@Override
	public boolean setPositionNonDirty() {
		// TODO ������������� ��������� �������� ������
		return super.setPositionNonDirty();
	}

	@Override
	public Entity getControllingPassenger() {
		// TODO ������������� ��������� �������� ������
		return super.getControllingPassenger();
	}

	@Override
	public List<Entity> getPassengers() {
		// TODO ������������� ��������� �������� ������
		return super.getPassengers();
	}

	@Override
	public boolean isPassenger(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.isPassenger(entityIn);
	}

	@Override
	public Collection<Entity> getRecursivePassengers() {
		// TODO ������������� ��������� �������� ������
		return super.getRecursivePassengers();
	}

	@Override
	public <T extends Entity> Collection<T> getRecursivePassengersByType(Class<T> entityClass) {
		// TODO ������������� ��������� �������� ������
		return super.getRecursivePassengersByType(entityClass);
	}

	@Override
	public Entity getLowestRidingEntity() {
		// TODO ������������� ��������� �������� ������
		return super.getLowestRidingEntity();
	}

	@Override
	public boolean isRidingSameEntity(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.isRidingSameEntity(entityIn);
	}

	@Override
	public boolean isRidingOrBeingRiddenBy(Entity entityIn) {
		// TODO ������������� ��������� �������� ������
		return super.isRidingOrBeingRiddenBy(entityIn);
	}

	@Override
	public Entity getRidingEntity() {
		// TODO ������������� ��������� �������� ������
		return super.getRidingEntity();
	}

	@Override
	public EnumPushReaction getPushReaction() {
		// TODO ������������� ��������� �������� ������
		return super.getPushReaction();
	}

	@Override
	protected int getFireImmuneTicks() {
		// TODO ������������� ��������� �������� ������
		return super.getFireImmuneTicks();
	}

	public Vvsvsvsd(World worldIn) {
		super(worldIn);
		// TODO ������������� ��������� �������� ������������
	}

}
