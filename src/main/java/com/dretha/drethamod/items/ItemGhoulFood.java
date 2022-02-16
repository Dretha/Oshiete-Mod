package com.dretha.drethamod.items;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.entity.human.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGhoulFood extends ItemFood implements IHasModel{
	
	private String description;
	protected int satiation;
	protected EnumRarity rarity = EnumRarity.COMMON;
	protected EnumAction action = EnumAction.EAT;

	public ItemGhoulFood(String name, String description, int amount, float saturation, int satiation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		construct(name, description, amount, saturation, satiation, isWolfFood);
        
        InitItems.ITEMS.add(this);
	}

	public ItemGhoulFood(String name, String description, int amount, float saturation, int satiation, boolean isWolfFood, EnumRarity rarity) {
		super(amount, saturation, isWolfFood);
		construct(name, description, amount, saturation, satiation, isWolfFood);
		this.rarity = rarity;

		InitItems.ITEMS.add(this);
	}
	public ItemGhoulFood(String name, String description, int amount, float saturation, int satiation, boolean isWolfFood, EnumRarity rarity, EnumAction action) {
		super(amount, saturation, isWolfFood);
		construct(name, description, amount, saturation, satiation, isWolfFood);
		this.rarity = rarity;
		this.action = action;

		InitItems.ITEMS.add(this);
	}
	private void construct(String name, String description, int amount, float saturation, int satiation, boolean isWolfFood) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(ModCreativeTabs.GENERAL);
		this.description = description;
		this.satiation = satiation;
		setMaxStackSize(64);
		if (this instanceof Kakuho) {
			setMaxStackSize(1);
		}
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
	    desc.add(description);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity;
	}


	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if (!worldIn.isRemote) {
			PersonStats stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();

			if (stats.isGhoul()) {
				stats.addRCpoints(satiation, player);
				stats.addRClevel(satiation);
			}
		}
	}

	public void onFoodEatenHuman(ItemStack stack, EntityHuman human)
	{
		PersonStats stats = PersonStats.getStats(human);

		if (stats.isGhoul()) {
			if (this instanceof Kakuho) {
				int satiation = stack.getTagCompound().getInteger("RCpoints");
				GhoulType ghoulType = ((Kakuho) this).getGhoulType();
				stats.addRCpoints((int) ((float) satiation / 7.8125F), human);
				if (stats.getGhoulType() == ghoulType)
					stats.addRCpoints((int) ((float) satiation / 7.8125F), human);
			}
			else stats.addRCpoints(satiation, human);
		}
	}

	public int getSatiation() {
		return this.satiation;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return itemUseDuration;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return action;
	}
}
