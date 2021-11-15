package com.dretha.drethamod.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemTabletCreative extends Item implements IHasModel{
	
	private int ghoulType;
	private String description;
	
	public ItemTabletCreative(String name, String description, int ghoulType) {
		this.maxStackSize = 1;
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTab.CTAB);
        this.ghoulType = ghoulType;
        this.description = description;
        InitItems.ITEMS.add(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ICapaHandler capa = playerIn.getCapability(CapaProvider.PLAYER_CAP, null);
		if (ghoulType!=0) {
		    if (!capa.isGhoul()) {
		    	capa.becomeGhoul(true, ghoulType, playerIn);
		    }
		} else {
			capa.becomeHuman(playerIn);
		}
		
		playerIn.inventory.clearMatchingItems(this, -1, 1, null);
		playerIn.world.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1F, 1F);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
	    desc.add(description);
	}
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
}
