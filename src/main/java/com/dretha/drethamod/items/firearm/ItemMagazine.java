package com.dretha.drethamod.items.firearm;

import com.dretha.drethamod.capability.firearm.CapaFirearmProvider;
import com.dretha.drethamod.capability.firearm.ICapaFirearmHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.EnumKeeper;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagazine extends Item implements IHasModel {

    public static final int STANDART_MAGAZINE_CAPACITY = 64;

    public ItemMagazine(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.WEAPON);
        setMaxStackSize(1);

        InitItems.ITEMS.add(this);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        if (!world.isRemote)
            world.playSound(null, player.getPosition(), InitSounds.hk33replace, SoundCategory.PLAYERS, 1.0F, 1.0F);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public void onUsingTick(ItemStack magazine, EntityLivingBase base, int count1)
    {
        if (!(base instanceof EntityPlayer) || base.ticksExisted%3!=0) return;

        EntityPlayer player = (EntityPlayer) base;

        ICapaFirearmHandler capa = magazine.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        int ammo = capa.getAmmo();
        Bullets magazineBulletType = capa.getBullets();
        ItemStack bullet = player.getHeldItemOffhand();

        if (bullet.getItem() instanceof ItemBullet)
        {
            ItemBullet itemBullet = (ItemBullet) bullet.getItem();
            Bullets bulletType = itemBullet.getBulletType();

            if ((bulletType == magazineBulletType || capa.isEmpty()) && ammo < STANDART_MAGAZINE_CAPACITY)
            {
                if (!base.world.isRemote)
                    SoundPlayer.play(player, InitSounds.hk33replace);

                player.getHeldItemOffhand().shrink(1);
                player.inventoryContainer.detectAndSendChanges();
                capa.addAmmo(bulletType);
            }
        }
        else if (bullet.isEmpty() && !capa.isEmpty() && player.getItemInUseCount()<20)
        {
            nullMagazine(magazine);
            player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(magazineBulletType.getItemBullet(), ammo));
        }
    }
    @Override
    public ItemStack onItemUseFinish(ItemStack magazine, World world, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
        }
        return super.onItemUseFinish(magazine, world, entityLiving);
    }

    private void nullMagazine(ItemStack stack) {
        ICapaFirearmHandler magazineCapa = stack.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        magazineCapa.setAmmo(0, Bullets.NONE);
    }

    @Override
    public void addInformation(ItemStack magazine, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        super.addInformation(magazine, worldIn, desc, flagIn);
        ICapaFirearmHandler capa = magazine.getCapability(CapaFirearmProvider.FIREARM_CAP, null);
        if (!capa.isEmpty())
        {
            int count = capa.getAmmo();
            desc.add(count + "/" + STANDART_MAGAZINE_CAPACITY + I18n.format("info.ammo"));
            Bullets bullet = capa.getBullets();
            desc.add(I18n.format(bullet.getDescription()));
        } else {
            desc.add(I18n.format("info.empty"));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumKeeper.FIREARM;
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
