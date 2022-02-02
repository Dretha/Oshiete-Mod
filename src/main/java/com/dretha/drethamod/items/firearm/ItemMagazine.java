package com.dretha.drethamod.items.firearm;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.EnumKeeper;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagazine extends Item implements IHasModel {

    public static final int STANDART_MAGAZINE_CAPACITY = 64;
    public static final int STANDART_RELOAD_COUNT = STANDART_MAGAZINE_CAPACITY/8;

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

    /*


            int magazineCount = magazine.getTagCompound().getInteger("count");
            Bullets magazineBullets = Bullets.valueOf(magazine.getTagCompound().getString("bullet"));

            for (ItemStack bullet : player.inventory.offHandInventory)
            {
                if (bullet.getItem() instanceof ItemBullet) //зарядка патронов в магазин
                {
                    ItemBullet itemBullet = (ItemBullet) bullet.getItem();

                    if ((itemBullet.getBulletType() == magazineBullets || magazineBullets == Bullets.NONE) && magazineCount < STANDART_MAGAZINE_CAPACITY)
                    {
                        if (!world.isRemote)
                            world.playSound(null, player.getPosition(), InitSounds.hk33replace, SoundCategory.PLAYERS, 1.0F, 1.0F);

                        player.inventory.offHandInventory.clear();
                        player.inventoryContainer.detectAndSendChanges();

                        NBTTagCompound compound = new NBTTagCompound();
                        compound.setString("bullet", itemBullet.getBulletType().toString());
                        compound.setInteger("count", bullet.getCount() + magazineCount);
                        magazine.setTagCompound(compound);
                        break;
                    }
                }
                else if (bullet.isEmpty() && magazineCount > 0) //вытащить патроны в руку
                {
                    ItemStack bullets = new ItemStack(magazineBullets.getItemBullet(), magazineCount);
                    player.inventory.addItemStackToInventory(bullets);
                    player.inventoryContainer.detectAndSendChanges();

                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setString("bullet", Bullets.NONE.toString());
                    compound.setInteger("count", 0);
                    magazine.setTagCompound(compound);
                }
            }
        }

    }
     */

    @Override
    public void onUsingTick(ItemStack magazine, EntityLivingBase base, int count1)
    {
        if (!(base instanceof EntityPlayer) || base.ticksExisted%20!=0) return;

        EntityPlayer player = (EntityPlayer) base;
        World world = base.world;

        int magazineCount = magazine.getTagCompound().getInteger("count");
        Bullets magazineBullets = Bullets.valueOf(magazine.getTagCompound().getString("bullet"));

        for (ItemStack bullet : player.inventory.offHandInventory)
        {
            if (bullet.getItem() instanceof ItemBullet)
            {

                ItemBullet itemBullet = (ItemBullet) bullet.getItem();
                Bullets bulletType = itemBullet.getBulletType();
                int bulletCount = bullet.getCount();

                //зарядка патронов в магазин
                if ((bulletType == magazineBullets || magazineBullets == Bullets.NONE) && magazineCount < STANDART_MAGAZINE_CAPACITY)
                {
                    if (!world.isRemote)
                        world.playSound(null, player.getPosition(), InitSounds.hk33replace, SoundCategory.PLAYERS, 1.0F, 1.0F);

                    player.inventory.offHandInventory.clear();
                    player.inventoryContainer.detectAndSendChanges();


                    NBTTagCompound compound = new NBTTagCompound();
                    int finalMagazineCount;

                    //патронов надо больше= 8
                    if (STANDART_MAGAZINE_CAPACITY - magazineCount >= STANDART_RELOAD_COUNT) {
                        //пуль есть меньше= 8
                        if (bulletCount <= STANDART_RELOAD_COUNT) {
                            finalMagazineCount = bulletCount + magazineCount;
                        } else {
                            finalMagazineCount = STANDART_RELOAD_COUNT + magazineCount;
                            player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(itemBullet, bulletCount - STANDART_RELOAD_COUNT));
                        }
                    } else {
                        //пуль есть меньше= чем требуется
                        if (bulletCount <= STANDART_MAGAZINE_CAPACITY - magazineCount) {
                            finalMagazineCount = bulletCount + magazineCount;
                        } else {
                            int excessCount = (magazineCount + bulletCount) - STANDART_MAGAZINE_CAPACITY;
                            finalMagazineCount = STANDART_MAGAZINE_CAPACITY;
                            player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(itemBullet, excessCount));
                        }
                    }

                    compound.setInteger("count", finalMagazineCount);
                    compound.setString("bullet", bulletType.toString());
                    magazine.setTagCompound(compound);

                //вытащить патроны в руку
                }
                else if (bullet.isEmpty() && magazineCount>0 && player.ticksExisted%30==0)
                {
                    nullMagazine(magazine);
                    player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(magazineBullets.getItemBullet(), magazineCount));
                }
            }
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

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        nullMagazine(stack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!stack.hasTagCompound()) {
            nullMagazine(stack);
        }
    }

    private void nullMagazine(ItemStack stack) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("bullet", Bullets.NONE.toString());
        compound.setInteger("count", 0);
        stack.setTagCompound(compound);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, desc, flagIn);
        if (stack.hasTagCompound() && Bullets.valueOf(stack.getTagCompound().getString("bullet")) != Bullets.NONE)
        {
            NBTTagCompound compound = stack.getTagCompound();
            int count = compound.getInteger("count");
            desc.add(count + "/" + STANDART_MAGAZINE_CAPACITY + I18n.format("info.ammo"));
            Bullets bullet = Bullets.valueOf(compound.getString("bullet"));
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
