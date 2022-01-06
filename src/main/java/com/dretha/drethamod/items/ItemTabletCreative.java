package com.dretha.drethamod.items;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTabletCreative extends Item implements IHasModel{
	
	private GhoulType ghoulType;
	private String description;
	
	public ItemTabletCreative(String name, String description, GhoulType ghoulType) {
		this.maxStackSize = 64;
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        this.ghoulType = ghoulType;
        this.description = description;
        InitItems.ITEMS.add(this);
	}

	public GhoulType getGhoulType() {
		return ghoulType;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ICapaHandler capa = playerIn.getCapability(CapaProvider.PLAYER_CAP, null);
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			if (ghoulType!=GhoulType.NONE) {
				capa.becomeHuman(playerIn);
				capa.becomeGhoul(ghoulType, playerIn);
				if (capa.isKaguneActive())
					capa.updateEntityKagune();
			} else {
				capa.becomeHuman(playerIn);
			}
		
			playerIn.inventory.clearMatchingItems(this, -1, 1, null);
			playerIn.world.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1F, 1F);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
	    desc.add(description);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumKeeper.LEGENDARY;
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
}
