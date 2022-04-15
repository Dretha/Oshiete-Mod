package com.dretha.drethamod.items;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import org.lwjgl.input.Keyboard;

public class Syringe extends Item implements IHasModel {

    protected final EnumRarity rarity;

    public Syringe(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        setMaxStackSize(1);
        this.rarity = EnumRarity.COMMON;

        InitItems.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            takeBlood(player, player);
        return super.onItemRightClick(world, player, hand);
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
    {
        if (PersonStats.getStats(target)!=PersonStats.EMPTY)
            takeBlood(player, target);
        return super.itemInteractionForEntity(stack, player, target, hand);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTagCompound() && stack.getItem()==InitItems.BLOOD_SYRINGE) {
            NBTTagCompound compound = new NBTTagCompound();
            PersonStats.EMPTY.writeToNBT(compound);
            stack.setTagCompound(compound);
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    protected void takeBlood(EntityPlayer attacker, EntityLivingBase target) {
        ItemStack stack = attacker.getHeldItemMainhand();
        if (stack.getItem()==InitItems.EMPTY_SYRINGE) {
            SoundPlayer.play(attacker, SoundEvents.BLOCK_GRASS_STEP);
            stack.shrink(1);
            target.attackEntityFrom(DamageSource.causePlayerDamage(attacker), 1);

            ItemStack bloodSyringe = new ItemStack(InitItems.BLOOD_SYRINGE);
            NBTTagCompound compound = new NBTTagCompound();

            if (target instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) target;
                ICapaHandler capa = EventsHandler.getCapaMP(entityPlayer);
                compound.setString("name", entityPlayer.getDisplayNameString());
                compound.setInteger("smell", capa.getSmellController().getRadius());
            }

            PersonStats.getStats(target).writeToNBT(compound);
            bloodSyringe.setTagCompound(compound);

            attacker.addItemStackToInventory(bloodSyringe);
            attacker.inventoryContainer.detectAndSendChanges();
        }
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
