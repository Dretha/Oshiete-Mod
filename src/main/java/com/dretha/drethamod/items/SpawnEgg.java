package com.dretha.drethamod.items;

import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.kuinkes.QColdSteel;
import com.dretha.drethamod.items.kuinkes.Weapons;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class SpawnEgg extends Item implements IHasModel {

    public SpawnEgg(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(64);

        InitItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
    {
        RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
        if (objectMouseOver == null) return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(handIn));

        ItemStack itemstack = player.getHeldItem(handIn);

        if (!player.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        if (!worldIn.isRemote)
        {
            BlockPos pos = objectMouseOver.getBlockPos();
            //pos.up().north().west();
            EntityHuman human = null;
            if (getUnlocalizedName().contains("ghoul"))
                human = getGhoul(worldIn, pos);
            else if (getUnlocalizedName().contains("dove"))
                human = getDove(worldIn, pos);
            worldIn.spawnEntity(human);
        }
// TODO сделать правильный спавн из €иц
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    private EntityHuman getGhoul(World world, BlockPos pos) {
        EntityHuman human = new EntityHuman(world, pos);
        PersonStats stats = human.personStats();
        stats.becomeGhoul(GhoulType.random(), human);
        stats.addRCpoints(Oshiete.random.nextInt(3500), human);
        stats.addSkill(Oshiete.random.nextInt(1500), human);
        stats.releaseKagune(human);
        return human;
    }
    private EntityHuman getDove(World world, BlockPos pos) {
        EntityHuman human = new EntityHuman(world, pos);
        PersonStats stats = human.personStats();
        stats.becomeDove(human);
        stats.addSkill(Oshiete.random.nextInt(3200), human);
        stats.updateCharacteristics(human);
        armTheDove(human);
        return human;
    }

    public static void armTheDove(EntityHuman dove)
    {
        PersonStats stats = dove.personStats();
        dove.setHeldItem(EnumHand.MAIN_HAND, QColdSteel.buildRandomColdSteel(stats.getSkill()));

        QColdSteel coldSteel = (QColdSteel) dove.getHeldItemMainhand().getItem();
        if (coldSteel.getWeapon()== Weapons.KNIFE && Oshiete.random.nextBoolean())
            dove.setHeldItem(EnumHand.OFF_HAND, dove.getHeldItemMainhand());
        if (coldSteel.getWeapon()== Weapons.KATANA && Oshiete.random.nextBoolean() && Oshiete.random.nextBoolean())
            dove.setHeldItem(EnumHand.OFF_HAND, QColdSteel.buildRandomColdSteel(stats.getSkill(), Weapons.KNIFE));
        if (Oshiete.random.nextInt(100)<6)
            dove.setHeldItem(EnumHand.OFF_HAND, dove.getHeldItemMainhand());

        stats.getInventory().setInventorySlotContents(((IDressable)InitItems.KUREO_CAPE).getSlot(), new ItemStack(InitItems.KUREO_CAPE));
    }
}
