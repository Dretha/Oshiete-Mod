package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.ListStackUtils;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.stats.PersonStats;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.ArrayList;

public class EntityCorpse extends EntityLiving implements IEntityAdditionalSpawnData {

    public static final ResourceLocation LOOT = new ResourceLocation(Reference.MODID, "loot_tables/corpse.json");
    protected ResourceLocation textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/skin0002.png");
    public static final int ticksBeforeUndead = 10800;
    protected String skin = "skin0002";
    protected PersonStats stats = new PersonStats();
    protected int rotationAngle = 0;
    protected ArrayList<ItemStack> drop;
    protected ActionController dissectionController = new ActionController(17);
    protected boolean isEmbalmed = false;

    public EntityCorpse(World worldIn, BlockPos pos, String texture, boolean isGhoul, int shards, int rotationAngle, ClothesInventory inventory, ArrayList<ItemStack> drop) {
        super(worldIn);

        float width = 1F;
        setSize(width, width);
        this.setEntityInvulnerable(true);
        this.setHealth(20);

        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
        skin = texture;
        this.stats.setKakugan(SkinHandler.getKey(skin));
        this.textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/"+ skin + ".png");
        this.stats.setGhoul(isGhoul);
        this.stats.setKakuganActive(isGhoul);
        stats.setShardCountInEntity(shards);
        stats.setInventory(inventory);
        this.rotationAngle = rotationAngle;
        this.drop = drop;
    }
    public EntityCorpse(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (isEmbalmed) return;

        if (this.ticksExisted > ticksBeforeUndead && !world.isRemote) {
            setDead();
            EntityMob mob;

            if (world.isDaytime()) {
                mob = new EntityHusk(world);
            }
            else
            {
                if (ListStackUtils.containJustOneTypeItem(drop, Items.BONE))
                    mob = new EntitySkeleton(world);
                else
                    mob = new EntityZombie(world);
            }

            mob.setPosition(posX, posY, posZ);
            world.spawnEntity(mob);
        }

        if (ticksExisted%2==0 && this.ticksExisted > ticksBeforeUndead/2)
        {
            double r = 67D/255D;
            double g = 79D/255D;
            double b = 44D/255D;
            this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, r, g, b);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        personStats().writeToNBT(compound);

        compound.setString("skin", skin);
        compound.setInteger("angle", rotationAngle);
        compound.setBoolean("isEmbalmed", isEmbalmed);
        compound.setInteger("ticksExisted", ticksExisted);
        ListStackUtils.writeToNBT(drop, compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        personStats().readFromNBT(compound);

        skin = compound.getString("skin");
        textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/"+ skin + ".png");
        rotationAngle = compound.getInteger("angle");
        isEmbalmed = compound.getBoolean("isEmbalmed");
        ticksExisted = compound.getInteger("ticksExisted");
        drop = ListStackUtils.readFromNBT(compound);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote && cause.isFireDamage()) {
            this.entityDropItem(new ItemStack(Items.COAL, Oshiete.random.nextInt(4)+4, 0), 0);
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.getHeldItemMainhand().isEmpty() && !isEmbalmed) {
            player.swingArm(EnumHand.MAIN_HAND);

            if (dissectionController.endAct(player.ticksExisted) && !drop.isEmpty() && !world.isRemote) {
                dissectionController.setTicksPre(player.ticksExisted);
                ItemStack stack = drop.get(drop.size() - 1);
                drop.remove(drop.size() - 1);
                this.entityDropItem(stack, 0);
                if (!world.isRemote)
                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SLIME_HURT, SoundCategory.PLAYERS, 1F, 1F);
            } else if (drop.isEmpty()) {
                this.setDead();
            }
        }

        if (player.getHeldItemMainhand().getItem() == InitItems.BALSAM && !isEmbalmed) {
            if (!player.isCreative())
                player.getHeldItemMainhand().shrink(1);
            isEmbalmed = true;
        }

        return super.processInteract(player, hand);
    }

    public ResourceLocation getSkinTexture() {
        return textureLocation;
    }

    public PersonStats personStats() {
        return stats;
    }

    @Override
    public void writeSpawnData(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, skin);
        ByteBufUtils.writeVarInt(buf, rotationAngle, 5);

        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("isEmbalmed", isEmbalmed);
        compound.setInteger("ticksExisted", ticksExisted);
        stats.writeToNBT(compound);
        ListStackUtils.writeToNBT(drop, compound);
        ByteBufUtils.writeTag(buf, compound);
    }

    @Override
    public void readSpawnData(ByteBuf buf) {
        skin = ByteBufUtils.readUTF8String(buf);
        textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/"+ skin + ".png");
        rotationAngle = ByteBufUtils.readVarInt(buf, 5);

        NBTTagCompound compound = ByteBufUtils.readTag(buf);
        isEmbalmed = compound.getBoolean("isEmbalmed");
        ticksExisted = compound.getInteger("ticksExisted");
        stats.readFromNBT(compound);
        drop = ListStackUtils.readFromNBT(compound);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return InitSounds.burning;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return InitSounds.burning;
    }
}
