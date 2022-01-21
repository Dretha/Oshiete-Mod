package com.dretha.drethamod.entity.human;

import com.dretha.drethamod.client.inventory.ClothesInventory;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.DropMessage;
import com.dretha.drethamod.server.KaguneImpactMessage;
import com.dretha.drethamod.utils.ListStackUtils;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.dretha.drethamod.utils.stats.PersonStats;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import java.util.ArrayList;

public class EntityCorpse extends EntityLiving implements IEntityAdditionalSpawnData {

    protected ResourceLocation textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/skin0002.png");
    protected String skin = "skin0002";
    protected PersonStats stats = new PersonStats();
    protected int rotationAngle = 0;
    protected ArrayList<ItemStack> drop;
    protected ActionController dissectionController = new ActionController(17);

    public EntityCorpse(World worldIn, BlockPos pos, String texture, boolean isGhoul, int shards, int rotationAngle, ClothesInventory inventory, ArrayList<ItemStack> drop) {
        super(worldIn);

        float width = 1.5F;
        setSize(width, width);
        this.setEntityInvulnerable(true);
        this.setHealth(1);

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
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        personStats().writeToNBT(compound);

        compound.setString("skin", skin);
        compound.setInteger("angle", rotationAngle);
        ListStackUtils.writeToNBT(drop, compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        personStats().readFromNBT(compound);

        skin = compound.getString("skin");
        textureLocation = new ResourceLocation(Reference.MODID, "textures/entity/skins/"+ skin + ".png");
        rotationAngle = compound.getInteger("angle");
        drop = ListStackUtils.readFromNBT(compound);
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        player.swingArm(EnumHand.MAIN_HAND);

        if (dissectionController.endAct(player.ticksExisted) && !drop.isEmpty() && !world.isRemote)
        {
            dissectionController.setTicksPre(player.ticksExisted);
            ItemStack stack = drop.get(drop.size()-1);
            drop.remove(drop.size()-1);
            this.entityDropItem(stack, 0);
        }
        else if (drop.isEmpty())
        {
            this.setDead();
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
        stats.readFromNBT(compound);
        drop = ListStackUtils.readFromNBT(compound);
    }
}
