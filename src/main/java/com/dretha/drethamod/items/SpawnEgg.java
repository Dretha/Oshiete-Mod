package com.dretha.drethamod.items;

import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.kuinkes.QColdSteel;
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
        ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
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
            pos.up().north().west();
            EntityHuman human = null;
            if (getUnlocalizedName().contains("ghoul"))
                human = getGhoul(worldIn, pos);
            else if (getUnlocalizedName().contains("dove"))
                human = getDove(worldIn, pos);
            worldIn.spawnEntity(human);
        }

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
        stats.setDove(true);
        stats.addSkill(700, human);
        stats.addSkill(Oshiete.random.nextInt(3500), human);
        human.setHeldItem(EnumHand.MAIN_HAND, QColdSteel.randomModificateWeapon(stats.getSkill()));
        stats.getInventory().setInventorySlotContents(((IDressable)InitItems.KUREO_CAPE).getSlot(), new ItemStack(InitItems.KUREO_CAPE));
        return human;
    }
}
