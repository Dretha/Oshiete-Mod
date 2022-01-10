package com.dretha.drethamod.entity;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.entity.ai.EntityAIGhoulAttack;
import com.dretha.drethamod.entity.human.HumanTexHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.ItemTabletCreative;
import com.dretha.drethamod.items.Kakuho;
import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.kuinkes.IKuinke;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.enums.HandType;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.enums.UkakuState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EntityHuman extends EntityMob implements IAnimals{
	
	Random random = new Random();
	
	private int RCpoints = random.nextInt(501)+200;
	private int RClevel = RCpoints/10;
	private boolean isGhoul = false;
	private int skillPoints = 0;
	private GhoulType ghoulType = GhoulType.NONE;
	private UkakuState ukakuState = UkakuState.NONE;
	private HandType handType = HandType.RIGHT;
	private ImpactType impactType = ImpactType.THRUST;
	
	private EntityKagune entityKagune = null;
	private String skin_variant;
	private ResourceLocation texture = null;

	private ArrayList<ItemStack> clothesinventory = new ArrayList<>(Arrays.asList(new ItemStack(Items.AIR), new ItemStack(Items.AIR), new ItemStack(Items.AIR), new ItemStack(Items.AIR)));

	private boolean isBlock = false;
	
	private int MODEL_VARIANT = 1;
	private int TEXTURE_VARIANT = 1;
	
	private boolean isKaguneActive = false;
	private boolean isKakuganActive = false;
	
	private int spawnKagunePatriclesTicksPre = 0;
	private boolean spawnKagunePatriclesFlag = false;
	
	private boolean isSpeedModeActive = false;
	
	private int shardCountInEntity = 0;
	
	private boolean admit = false;
	private int admitTicksPre = 0;
	
	public EntityHuman(World worldIn) {
		super(worldIn);

		float width = 0.6f;
		setSize(width, width*3);

		if (skin_variant==null)
		skin_variant = HumanTexHandler.getRandomTexture();
		/*
		NBTTagCompound compound = new NBTTagCompound();
		compound.setUniqueId("uuid", this.entityUniqueID);
		compound.setString("skin", skin_variant);
		Main.NETWORK.sendToServer(new DoSomeEntityMessage(compound));
		*/

	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (admit && admitTicksPre+400<=this.ticksExisted) {
			updateSpeedAttribute();
			admitKagune();
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		
		compound.setBoolean("isGhoul", this.isGhoul());
	    compound.setString("ghoulType", this.getGhoulType().toString());
	    compound.setString("ukakuState", this.ukakuState().toString());
	    compound.setString("handType", this.handType().toString());
	    compound.setInteger("RCpoints", this.getRCpoints());
	    compound.setInteger("RClevel", this.getRClevel());
	    compound.setInteger("skillPoints", this.getSkill());
	    compound.setBoolean("isKaguneActive", this.isKaguneActive());
	    compound.setBoolean("isKakuganActive", this.isKakuganActive());
	    compound.setInteger("shardCountInEntity", this.shardCountInEntity);
		compound.setString("skin_variant", skin_variant);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		this.isGhoul = compound.getBoolean("isGhoul");
		this.ghoulType = GhoulType.valueOf(compound.getString("ghoulType"));
		this.setUkakuState(UkakuState.valueOf(compound.getString("ukakuState")));
		this.setHandType(HandType.valueOf(compound.getString("handType")));
		this.setRCpoints(compound.getInteger("RCpoints"));
		this.setRClevel(compound.getInteger("RClevel"));
		this.setSkill(compound.getInteger("skillPoints"));
		this.setIsKaguneActive(compound.getBoolean("isKaguneActive"));
		this.setActivatedKakugan(compound.getBoolean("isKakuganActive"));
		this.shardCountInEntity = compound.getInteger("shardCountInEntity");
		this.skin_variant = compound.getString("skin_variant");
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (!world.isRemote) {
			if (!isGhoul)
			{
				this.entityDropItem(new ItemStack(InitItems.HUMAN_EYE, 2), 0);
				this.entityDropItem(new ItemStack(InitItems.HUMAN_MEAT, random.nextInt(2) + 1), 0);
			}
			else
			{
				ItemStack kakuho = new ItemStack(ghoulType.kakuho, 1);
				NBTTagCompound compound = new NBTTagCompound();
				compound.setInteger("RCpoints", this.RCpoints);
				kakuho.setTagCompound(compound);
				if (random.nextBoolean()) {
					this.entityDropItem(kakuho, 0);
				}
				this.entityDropItem(new ItemStack(InitItems.GHOUL_MEAT, random.nextInt(2) + 1), 0);
				if (this.isKaguneActive)
					this.entityDropItem(new ItemStack(InitItems.KAGUNE_SHARD, random.nextInt(2) + 2 + this.rank()), 0);
			}
		}
	}

	public ResourceLocation getSkinTexture() {
		if (texture==null)
			texture = new ResourceLocation("dm:textures/entity/skins/"+ skin_variant + ".png");
		return texture;
	}
	public String getSkin() {
		return skin_variant;
	}
	public void setSkin(String skin) {
		skin_variant = skin;
	}
	public Item getKakugan() {
		return HumanTexHandler.getKakugan(skin_variant);
	}

	public ArrayList<ItemStack> getInventory() {
		return clothesinventory;
	}
	
	public ImpactType getImpactType() {
		return impactType;
	}
	
	
	public void setAdmit(boolean b) {
		this.admit = b;
	}
	public void setAdmitTicksPre(int ticks) {
		this.admitTicksPre = ticks;
	}
	
	
	public EntityKagune getKagune() {
		return entityKagune;
	}
	
	
	
	public boolean isGhoul() {
		return isGhoul;
	}
	
	
	public int getShardCount() {
		return this.shardCountInEntity;
	}
	
	public void addShard() {
		this.shardCountInEntity++;
	}
	
	public void nullableShards() {
		this.shardCountInEntity=0;
	}
	
	
	
	public void removeRCpoints(int points) {
		this.RCpoints -= points;
		 
		 if (this.RCpoints < 0) this.RCpoints = 0;
		
	}

	public void addRCpoints(int points) {
		this.RCpoints += points;		
	}

	public void setRCpoints(int points) {
		this.RCpoints = points;		
	}

	public int getRCpoints() {
		return this.RCpoints;
	}

	
	
	
	public void removeRClevel(int points) {
		updateRClevel();
		this.RClevel -= points;
		if (this.RClevel<0) this.RClevel=0;
	}

	public void addRClevel(int points) {
		updateRClevel();
		this.RClevel += points;	
		if (this.RClevel>this.RCpoints/10) this.RClevel=this.RCpoints/10;
	}

	public void updateRClevel() {
		this.RClevel = this.RCpoints/10 - (this.RCpoints/10 - this.RClevel);
	}
	
	public void setRClevel(int points) {
		this.RClevel=points;
	}

	public int getRClevel() {
		return this.RClevel;
	}
	
	public boolean RClevelFull() {
		return this.RClevel == this.RCpoints/10;
	}
	
	
	
	
	public int getSkill() {
		return this.skillPoints;
	}
	
	public void setSkill(int points) {
		this.skillPoints = points;
	}
	
	public void addSkill(int points) {
		this.skillPoints+=points;
	}

	public void removeSkill(int points) {
		this.skillPoints-=points;
		if (this.skillPoints < 0) this.skillPoints = 0;
	}



	public int rank()
	{
		float rankmeter = isGhoul ? (float)skillPoints/2+RCpoints : skillPoints+1000;
		int rank = Math.round(rankmeter/1000)-1;
		return rank;
	}
	public float exactRank() {
		float rankmeter = isGhoul ? (float)skillPoints/2+RCpoints : skillPoints+1000;
		float rank = rankmeter/1000;
		return rank;
	}



	public boolean isBlock() {
		return this.isBlock;
	}

	public void setBlock(boolean b) {
		this.isBlock = b;
	}

	
	
	public boolean isKakuganActive() {
		return this.isKakuganActive && this.isGhoul;
	}

	public void setActivatedKakugan(boolean activeKakugan) {
		this.isKakuganActive = activeKakugan;
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
	/*
	@Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.HUMAN;
    }
	*/
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIGhoulAttack(this, false));
        this.tasks.addTask(2, new EntityAIPanic(this, 3.0D));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityHuman.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        
        targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityHuman.class, true));
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
        		becomeHuman();
        	else
        		becomeGhoul(((ItemTabletCreative) item).getGhoulType());
        	
        	player.inventory.clearMatchingItems(item, -1, 1, null);
    		this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1F, 1F);
        }

		if (item == InitItems.HUMAN_MEAT) {
			if (this.world.isRemote)
				System.out.println("client");
			else
				System.out.println("server");
			player.sendMessage(new TextComponentString(skin_variant));
			player.sendMessage(new TextComponentString(HumanTexHandler.getKakugan(skin_variant).getUnlocalizedName()));
		}

		if (item instanceof IDressable) {
			IDressable dressable = (IDressable) item;
			clothesinventory.add(dressable.getSlot(), new ItemStack(item));
		}

		if (item instanceof IKuinke) {
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(item));
		}

		if (item instanceof ItemGhoulFood) {
			if (!(item instanceof Kakuho)) {
				this.addRCpoints(((ItemGhoulFood) item).getSatiation());
			} else {
				if (itemStack.hasTagCompound())
					this.addRCpoints(itemStack.getTagCompound().getInteger("RCpoints"));
				player.sendMessage(new TextComponentString(RCpoints + " RC Points"));
			}
		}





		return super.processInteract(player, hand);
	}
	
	
	public void becomeGhoul(GhoulType ghoulType) {
		this.isGhoul = true;
		this.ghoulType = ghoulType;
		if (ghoulType==GhoulType.UKAKU)
			this.ukakuState = UkakuState.generateState();
		this.RCpoints+=801;
		updateRClevel();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
	}

	public void becomeHuman() {
		this.isGhoul = false;
		Random random = new Random();
		this.RCpoints = random.nextInt(501)+200;
		this.updateRClevel();
		this.ghoulType = GhoulType.NONE;
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
	}
	
	public void nullKagune() {
		entityKagune = null;
	}
	
	public GhoulType getGhoulType() {
		return this.ghoulType;
	}
	
	public boolean ukaku() {
		return this.isGhoul && this.ghoulType==GhoulType.UKAKU;
	}
	
	public UkakuState ukakuState() {
		return this.ukakuState;
	}
	
	public void setUkakuState(UkakuState state) {
		this.ukakuState = state;
	}
	
	public HandType handType() {
		return this.handType;
	}
	
	public void setHandType(HandType type) {
		this.handType = type;
	}
	
	public boolean rightHanded() {
		return this.handType==HandType.RIGHT;
	}
	
	
	public boolean isKaguneActive() {
		return this.isKaguneActive && this.isGhoul;
	}
	
	public void setIsKaguneActive(boolean activeKagune) {
		this.isKaguneActive = activeKagune;
	}
	
	
	public void updateSpeedAttribute() {
		IAttributeInstance ins = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		ins.removeAllModifiers();
		if (this.isKaguneActive) ins.applyModifier(speedghoul);
	}
	
	
	AttributeModifier speedghoul = new AttributeModifier("speedghoul", 0.5, 2);
	
	public void releaseKagune() {
		
		if (this.isKaguneActive) return;
		
		this.spawnKagunePatriclesTicksPre = this.ticksExisted;
		this.spawnKagunePatriclesFlag = true;
		//Main.NETWORK.sendToAllAround(new MyMessage(master.posX, master.posY, master.posZ), new NetworkRegistry.TargetPoint(master.world.provider.getDimension(), master.posX, master.posY, master.posZ, 60));
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedghoul);
		
		this.isKaguneActive = true;
		this.isKakuganActive = true;

		this.updateEntityKagune();
		
		this.world.playSound(null, this.getPosition(), InitSounds.let_out_kagune, SoundCategory.PLAYERS, 1F, 1F);
	}
	
	public void admitKagune() {
		if (!this.isKaguneActive) return;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedghoul.getID()));
		
		entityKagune.setAdmit(true);
		entityKagune.setAdmitTicks(this.ticksExisted);
	}
	
	public void updateEntityKagune() {
		entityKagune=EnumKagune.valueOf(this.getEnumId()).getEntity(this);
	}
	
	public String getTextureLocation() {
		return String.format("textures/entity/kagune/kagune%d%02d%02d.png", getNNGT(), this.MODEL_VARIANT, this.TEXTURE_VARIANT);
	}
	
	private int getNNGT() {
		return this.getGhoulType() == null ? 2 : GhoulType.indexOf(this.getGhoulType())+1;
	}
	
	public String getEnumId() {
		return String.format("KAGUNE%d%02d", this.ghoulType.id(), this.MODEL_VARIANT);
	}
	
	public int getTextureVariant() {
		return this.TEXTURE_VARIANT;
	}
}
