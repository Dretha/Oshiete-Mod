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
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

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


    private EntityHuman getGhoul(World world) {
        EntityHuman human = new EntityHuman(world);
        PersonStats stats = human.personStats();
        stats.becomeGhoul(GhoulType.random(), human);
        stats.addRCpoints(Oshiete.random.nextInt(3500), human);
        stats.addSkill(Oshiete.random.nextInt(1500), human);
        stats.releaseKagune(human);
        return human;
    }
    private EntityHuman getDove(World world) {
        EntityHuman human = new EntityHuman(world);
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote)
        {
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && !(worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid))
            {
                double x = 0.5D;
                double y = 0.5D;
                double z = 0.5D;

                switch(raytraceresult.sideHit.getIndex()) {
                    case 0: {
                        --y;
                        break;
                    }
                    case 1: {
                        ++y;
                        break;
                    }
                    case 2: {
                        --z;
                        break;
                    }
                    case 3: {
                        ++z;
                        break;
                    }
                    case 4: {
                        --x;
                        break;
                    }
                    case 5: {
                        ++x;
                        break;
                    }
                    default:
                        break;
                }

                Entity entity = spawnCreature(worldIn, (double)blockpos.getX() + x, (double)blockpos.getY() + y, (double)blockpos.getZ() + z);

                if (entity instanceof EntityLivingBase && itemstack.hasDisplayName())
                {
                    entity.setCustomNameTag(itemstack.getDisplayName());
                }

                if (!playerIn.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }
            else
            {
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
        }
        else
        {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }
    }

    public Entity spawnCreature(World worldIn, double x, double y, double z)
    {
        EntityLiving entityLiving;

        if (getUnlocalizedName().contains("ghoul"))
            entityLiving = getGhoul(worldIn);
        else if (getUnlocalizedName().contains("dove"))
            entityLiving = getDove(worldIn);
        else
            entityLiving = new EntityZombie(worldIn);

        entityLiving.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entityLiving.rotationYawHead = entityLiving.rotationYaw;
        entityLiving.renderYawOffset = entityLiving.rotationYaw;
        entityLiving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityLiving)), null);
        worldIn.spawnEntity(entityLiving);
        entityLiving.playLivingSound();

        return entityLiving;
    }
}
