package com.dretha.drethamod.blocks.fusion_blast_furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class FusionBlastContainer extends Container {

    private final FusionBlastInventory inventory;

    public FusionBlastContainer(EntityPlayer player, InventoryPlayer playerInventory, FusionBlastInventory fusionBlastInv) {
        fusionBlastInv.openInventory(player);

        this.addSlotToContainer(new Slot(fusionBlastInv, 0, 51, 28));
        this.addSlotToContainer(new Slot(fusionBlastInv, 1, 71, 28));
        this.addSlotToContainer(new SlotFurnaceFuel(fusionBlastInv, 2, 23, 59));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, fusionBlastInv, 3, 134, 34));
        //this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, fusionBlastInv, 4, 116, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.inventory = fusionBlastInv;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID < inventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, inventory.getSizeInventory(), false)) {
                return null;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        inventory.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return inventory.isUsableByPlayer(player);
    }
}
