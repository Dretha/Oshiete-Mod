package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.entity.ai.*;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.ItemTabletCreative;
import com.dretha.drethamod.items.SpawnEgg;
import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.items.kuinkes.IKuinkeMelee;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.stats.PersonStats;
import com.dretha.drethamod.worldevents.HeadquartersCCG;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityHuman extends EntityMob implements IAnimals, IEntityAdditionalSpawnData
{
	PersonStats stats = new PersonStats();

	protected float speed = 1.2F;
	private String skin;
	private ResourceLocation texture = null;
	
	public EntityHuman(World worldIn)
	{
		super(worldIn);
		construct();
	}

	public EntityHuman(World worldIn, BlockPos pos)
	{
		super(worldIn);
		construct();
		setPosition(pos.getX(), pos.getY(), pos.getZ());
	}

	private void construct() {
		float width = 0.6f;
		setSize(width, width*3);

		if (skin ==null)
			skin = SkinHandler.getRandomTexture();
		stats.setKakuganResource(SkinHandler.getKakuganResource(skin));
	}

	public PersonStats personStats() {
		return stats;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		/*
		if (admit && admitTicksPre+400<=this.ticksExisted) {
			updateSpeedAttribute();
			admitKagune();
		}

		if (this.ticksExisted%30==0 && !stats.isAgressive(this)) {
			EntityLivingBase scary = heMeScared();
			if (scary != null)
				setRevengeTarget(heMeScared());
		}
		*/
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		
		personStats().writeToNBT(compound);

		compound.setString("skinVariant", skin);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		personStats().readFromNBT(compound);

		this.skin = compound.getString("skinVariant");
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {

			ArrayList<ItemStack> drop = new ArrayList<>();
			if (!stats.isGhoul())
			{
				drop.add(new ItemStack(InitItems.HUMAN_EYE));
				drop.add(new ItemStack(InitItems.HUMAN_EYE));

				for (int i=0; i <= Oshiete.random.nextInt(2) + 2 +stats.materialRank(); i++) {
					drop.add(new ItemStack(InitItems.HUMAN_MEAT));
				}

				for (int i=0; i <= Oshiete.random.nextInt(2) + 2; i++) {
					drop.add(new ItemStack(Items.BONE));
				}
			}
			else
			{
				drop.add(new ItemStack(InitItems.HUMAN_EYE));
				drop.add(new ItemStack(InitItems.HUMAN_EYE));

				for (int i=0; i <= Oshiete.random.nextInt(2) + 2 + stats.materialRank(); i++) {
					drop.add(new ItemStack(InitItems.GHOUL_MEAT));
				}

				for (int i=0; i <= Oshiete.random.nextInt(2) + 2; i++) {
					drop.add(new ItemStack(Items.BONE));
				}

				if (personStats().isKaguneActive())
					this.entityDropItem(new ItemStack(InitItems.KAGUNE_SHARD, Oshiete.random.nextInt(3) + 2 + stats.materialRank()), 0);

				if (Oshiete.random.nextInt(3)!=2) {
					ItemStack kakuho = new ItemStack(GhoulType.getKakuho(stats.getGhoulType()));
					NBTTagCompound compound = new NBTTagCompound();
					compound.setInteger("RCpoints", stats.getRCpoints());
					kakuho.setTagCompound(compound);
					drop.add(kakuho);
				}
			}

			int rotationAngle;

			if (cause.getTrueSource() instanceof EntityLivingBase)
				rotationAngle = (int) cause.getTrueSource().rotationYaw;
			else
				rotationAngle = Oshiete.random.nextInt(270);

			drop.add(ItemStack.EMPTY);
			Collections.reverse(drop);
			EntityCorpse corpse = new EntityCorpse(world, this.getPosition(), skin, stats.isGhoul(), stats.getShardCountInEntity(), rotationAngle, stats.getInventory(), drop);
			world.spawnEntity(corpse);
		}
	}

	public ResourceLocation getSkinTexture() {
		if (texture==null)
			texture = new ResourceLocation("dm:textures/entity/skins/"+ skin + ".png");
		return texture;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}


	public static void registerFixesHuman(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityHuman.class);
    }
	
	public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.world.getLight(blockpos) > 0 && super.getCanSpawnHere();
    }
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }
	
	protected boolean canDespawn()
    {
        return false;
    }

	protected void initEntityAI()
    {
		int AIPriority = 0;
        this.tasks.addTask(AIPriority++, new EntityAISwimming(this));
		this.tasks.addTask(AIPriority++, new HumanAIAttackMellee(this));
		//this.tasks.addTask(AIPriority++, new EntityAIAttackMelee(this, 1.5, true));
		this.tasks.addTask(AIPriority++, new HumanAIAvoidScary(this, 2));
        this.tasks.addTask(AIPriority++, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(AIPriority++, new EntityAIWatchClosest(this, EntityHuman.class, 6.0F));
        this.tasks.addTask(AIPriority++, new EntityAILookIdle(this));
        this.tasks.addTask(AIPriority++, new EntityAIWander(this, 1.0D));

		this.targetTasks.addTask(1, new HumanAISearchForAttackTargets(this));
		this.targetTasks.addTask(2, new HumanAISelfDefence(this));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
    }

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
		if (entitylivingbaseIn==this) return;
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		Item item = player.getHeldItem(hand).getItem();
		ItemStack stack = player.getHeldItem(hand);

        if (item instanceof ItemTabletCreative && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
        	if (((ItemTabletCreative) item).getGhoulType() == GhoulType.NONE)
				stats.becomeHuman(this);
        	else
				stats.becomeGhoul(((ItemTabletCreative) item).getGhoulType(), this);
        	
        	player.inventory.clearMatchingItems(item, -1, 1, null);
    		this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1F, 1F);
        }

		if (item == InitItems.HUMAN_MEAT) {
			if (this.world.isRemote)
				System.out.println("client");
			else
				System.out.println("server");
			player.sendMessage(new TextComponentString(skin));
		}

		if (item instanceof IDressable) {
			IDressable dressable = (IDressable) item;
			stats.getInventory().setInventorySlotContents(dressable.getSlot(), new ItemStack(item));
		}

		if (item instanceof IKuinke) {
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(item));
		}

		if (item instanceof ItemGhoulFood) {
			((ItemGhoulFood) item).onFoodEatenHuman(stack, this);
			player.sendMessage(new TextComponentString(stats.getRCpoints() + " RC Points"));
		}

		player.swingArm(EnumHand.MAIN_HAND);
		//player.swingArm(EnumHand.OFF_HAND);

		if (item==InitItems.CCG_SERTIFICATE && !personStats().isDove()) {
			stack.shrink(1);
			personStats().becomeDove(this);
			SpawnEgg.armTheDove(this);
		}

		return super.processInteract(player, hand);
	}

	@Override
	public void writeSpawnData(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, skin);

		NBTTagCompound compound = new NBTTagCompound();
		stats.writeToNBT(compound);
		ByteBufUtils.writeTag(buf, compound);
	}

	@Override
	public void readSpawnData(ByteBuf buf) {
		String tex = ByteBufUtils.readUTF8String(buf);
		texture = new ResourceLocation("dm:textures/entity/skins/"+ tex + ".png");

		stats.readFromNBT(ByteBufUtils.readTag(buf));
	}

	public boolean isScared()
	{
		int radius = 16;

		List<EntityLivingBase> entityLivingBases = this.world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(this.posX - radius, this.posY - 2, this.posZ - radius, this.posX + radius, this.posY + 4, this.posZ + radius));
		for (EntityLivingBase base : entityLivingBases) {
			if(base == this)continue;

			if (base instanceof EntityCorpse || PersonStats.getStats(base).isKakuganActive() || base.isCreatureType(EnumCreatureType.MONSTER, false))
				return true;
		}
		return false;
	}
	public EntityLivingBase heMeScared()
	{
		int radius = 16;

		List<EntityLivingBase> entityLivingBases = this.world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(this.posX - radius, this.posY - 2, this.posZ - radius, this.posX + radius, this.posY + 4, this.posZ + radius));
		for (EntityLivingBase base : entityLivingBases) {
			if(base == this)continue;

			if (base instanceof EntityCorpse || PersonStats.getStats(base).isKakuganActive() || (base.isCreatureType(EnumCreatureType.MONSTER, false) && !(base instanceof EntityHuman)))
				return base;
		}
		return null;
	}

	public boolean isMyEnemy(EntityLivingBase enemy) {
		PersonStats enemyStats = PersonStats.getStats(enemy);
		if (enemy instanceof EntityPlayer || enemy instanceof EntityHuman)
		{
			return (personStats().isGhoul() && !enemyStats.isGhoul()) || (personStats().isDove() && enemyStats.isKakuganActive()) || (personStats().isDove() && HeadquartersCCG.isWanted(enemy));
		}
		else {
			return enemy.isCreatureType(EnumCreatureType.MONSTER, false);
		}
	}

	public void setBlocking() {
		if (personStats().isBlock()) return;
		if ((stats.isGhoul() && !stats.ukaku()) && stats.isKaguneActive())
		{
			stats.setBlock(true);
		}
		else
		{
			if (melleeInMyMainhand())
				this.setActiveHand(EnumHand.MAIN_HAND);
			else if (melleeInMyOffhand())
				this.setActiveHand(EnumHand.OFF_HAND);
		}
	}

	public void resetBlocking() {
		if (!personStats().isBlock()) return;
		if ((stats.isGhoul() && !stats.ukaku()) && stats.isKaguneActive())
		{
			stats.setBlock(false);
		}
		else
		{
			this.resetActiveHand();
		}
	}

	protected boolean melleeInMyMainhand() {
		return this.getHeldItemMainhand().getItem() instanceof IKuinkeMelee;
	}
	protected boolean melleeInMyOffhand() {
		return this.getHeldItemOffhand().getItem() instanceof IKuinkeMelee;
	}
	protected boolean melleInMyHand() {
		return this.getHeldItemMainhand().getItem() instanceof IKuinkeMelee || this.getHeldItemOffhand().getItem() instanceof IKuinkeMelee;
	}
}
