package com.dretha.drethamod.items;

import com.dretha.drethamod.client.gui.GuiDocument;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;

public class Document extends Item implements IHasModel {

    protected final EnumRarity rarity;

    public Document(String name) {
        this(name, EnumRarity.COMMON);
    }
    public Document(String name, EnumRarity rarity) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        setMaxStackSize(1);
        this.rarity = rarity;

        InitItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItemMainhand();
        if (!world.isRemote) {
            RayTraceResult raytraceresult = this.rayTrace(world, player, true);
            BlockPos blockpos = raytraceresult.getBlockPos();
            IBlockState state = world.getBlockState(blockpos);
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && state.getBlock() == Blocks.CAULDRON) {
                int level = state.getValue(BlockCauldron.LEVEL);
                if (level > 0) {
                    level -= 1;
                    world.setBlockState(blockpos, state.withProperty(BlockCauldron.LEVEL, MathHelper.clamp(level, 0, 3)), 2);
                    player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.AIR));
                    player.addItemStackToInventory(new ItemStack(Items.PAPER));
                    player.inventoryContainer.detectAndSendChanges();
                }
            }
        }
        else if (stack.getItem()==InitItems.BLOOD_TEST)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiDocument(getBloodTest(stack)));
        }
        return super.onItemRightClick(world, player, hand);
    }

    private ArrayList<String> getBloodTest(ItemStack bloodTest)
    {
        ArrayList<String> text = new ArrayList<>();
        PersonStats stats = new PersonStats();
        stats.readFromNBT(bloodTest.getTagCompound());

        boolean isPlayer = bloodTest.getTagCompound().hasKey("name");
        if (isPlayer)
            text.add(I18n.format("string.name") + ": " + bloodTest.getTagCompound().getString("name"));
        text.add(I18n.format("string.rcpoints") + ": " + stats.getRCpoints());
        text.add(I18n.format("string.skill") + ": " + stats.getSkill());
        text.add(I18n.format("string.isghoul") + ": " + positiveOrNegative(stats.isGhoul()));

        if (stats.isGhoul()) {
            text.add(I18n.format("string.ghoultype") + ": " + stats.getGhoulType().description);
            text.add(I18n.format("string.rank") + ": " + I18n.format("rank." + Math.min(stats.rank(), 7)));
            text.add(I18n.format("string.attack") + ": " + stats.getDamage());
            text.add(I18n.format("string.protection") + ": " + stats.getProtection());
            if (isPlayer)
                text.add(I18n.format("string.smellradius") + ": " + bloodTest.getTagCompound().getInteger("smell"));
        }
        return text;
    }

    private String positiveOrNegative(boolean b) {
        if (b)
            return I18n.format("string.positive");
        else
            return I18n.format("string.negative");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound compound = new NBTTagCompound();
            PersonStats.EMPTY.writeToNBT(compound);
            stack.setTagCompound(compound);
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
