package com.dretha.drethamod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class KaguneBase extends Item{
	
	//dropped kagune
	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
        return 0;
    }
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
    	/*player.inventory.addItemStackToInventory(new ItemStack(item.getItem()));   		   
    	player.inventoryContainer.detectAndSendChanges(); */
        return false;
    }

	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
	
	
}
