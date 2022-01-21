package com.dretha.drethamod.entity;

import com.dretha.drethamod.entity.ai.EntityAIGhoulAttack;
import com.dretha.drethamod.entity.human.EntityCorpse;
import com.dretha.drethamod.entity.human.SkinHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.ItemTabletCreative;
import com.dretha.drethamod.items.Kakuho;
import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.stats.PersonStats;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Collections;

public class EntityHuman extends EntityMob implements IAnimals, IEntityAdditionalSpawnData
{
	PersonStats stats = new PersonStats();

	private String skin;
	private ResourceLocation texture = null;
	
	public EntityHuman(World worldIn) {
		super(worldIn);

		float width = 0.6f;
		setSize(width, width*3);

		if (skin ==null)
			skin = SkinHandler.getRandomTexture();
		stats.setKakugan(SkinHandler.getKey(skin));
		/*
		NBTTagCompound compound = new NBTTagCompound();
		compound.setUniqueId("uuid", this.entityUniqueID);
		compound.setString("skin", skin_variant);
		Main.NETWORK.sendToServer(new DoSomeEntityMessage(compound));
		*/
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
			/*
			if (!stats.isGhoul())
			{
				this.entityDropItem(new ItemStack(InitItems.HUMAN_EYE, 2), 0);
				this.entityDropItem(new ItemStack(InitItems.HUMAN_MEAT, Oshiete.random.nextInt(2) + 1), 0);
			}
			else
			{
				ItemStack kakuho = new ItemStack(stats.getGhoulType().kakuho, 1);
				NBTTagCompound compound = new NBTTagCompound();
				compound.setInteger("RCpoints", stats.getRCpoints());
				kakuho.setTagCompound(compound);
				if (Oshiete.random.nextBoolean()) {
					this.entityDropItem(kakuho, 0);
				}
				this.entityDropItem(new ItemStack(InitItems.GHOUL_MEAT, Oshiete.random.nextInt(2) + 1), 0);
				if (stats.isKaguneActive())
					this.entityDropItem(new ItemStack(InitItems.KAGUNE_SHARD, Oshiete.random.nextInt(2) + 2 + stats.rank()), 0);
			}
			*/


			ArrayList<ItemStack> drop = new ArrayList<>();
			if (!stats.isGhoul())
			{
				drop.add(new ItemStack(InitItems.HUMAN_EYE));
				drop.add(new ItemStack(InitItems.HUMAN_EYE));

				for (int i=0; i <= Oshiete.random.nextInt(2) + stats.rank(); i++) {
					drop.add(new ItemStack(InitItems.HUMAN_MEAT));
				}
			}
			else
			{
				drop.add(new ItemStack(InitItems.HUMAN_EYE));
				drop.add(new ItemStack(InitItems.HUMAN_EYE));

				for (int i=0; i <= Oshiete.random.nextInt(2) + stats.rank(); i++) {
					drop.add(new ItemStack(InitItems.GHOUL_MEAT));
				}

				for (int i=0; i <= Oshiete.random.nextInt(3) + stats.rank(); i++) {
					drop.add(new ItemStack(InitItems.KAGUNE_SHARD));
				}

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
	public Item getKakugan() {
		return SkinHandler.getKakuganItem(skin);
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
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIGhoulAttack(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 3.0D));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityHuman.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        
        targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityHuman.class, true));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
    }

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		Item item = player.getHeldItem(hand).getItem();
		ItemStack itemStack = player.getHeldItem(hand);

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
			player.sendMessage(new TextComponentString(SkinHandler.getKakuganItem(skin).getUnlocalizedName()));
		}

		if (item instanceof IDressable) {
			IDressable dressable = (IDressable) item;
			stats.getInventory().setInventorySlotContents(dressable.getSlot(), new ItemStack(item));
		}

		if (item instanceof IKuinke) {
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(item));
		}

		if (item instanceof ItemGhoulFood) {
			if (!(item instanceof Kakuho)) {
				stats.addRCpoints(((ItemGhoulFood) item).getSatiation());
			} else {
				if (itemStack.hasTagCompound())
					stats.addRCpoints(itemStack.getTagCompound().getInteger("RCpoints"));
				player.sendMessage(new TextComponentString(stats.getRCpoints() + " RC Points"));
			}
		}

		player.swingArm(EnumHand.MAIN_HAND);
		//player.swingArm(EnumHand.OFF_HAND);



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
}
