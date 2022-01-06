package com.dretha.drethamod.client.inventory;

import com.dretha.drethamod.items.clothes.IDressable;
import com.dretha.drethamod.items.clothes.ItemMask;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ClothesInventoryContainer  extends Container {


    /**
     * �����������
     * @param playerInventory ��������� ������
     * @param cInventory ��������� ���������
     * @param player �����
     */
    public ClothesInventoryContainer(InventoryPlayer playerInventory, ClothesInventory cInventory, EntityPlayer player) {

        //��������� 8 ��������� ������. ���������: �����, ��������� � �������� ��� ���������, ������ �����, � ����������, � ����������
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 0, 54, 6));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 1, 54, 33));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 2, 108, 6));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 3, 108, 33));

        //��������� 27 ��������� ������ ��������� ������
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(playerInventory, j1 + (l + 1) * 9, 9 + j1 * 18, 54 + l * 18));
            }
        }

        //� ��� �� ��������� 9 ��������� ������ � ������
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(playerInventory, i1, 9 + i1 * 18, 112));
        }
    }

    /**
     * ���� ����� ����������� ����� ����� �������� ���� � ������� �� ���� � ����� ����������� �������.
     * ����� �� ������ ������ ������ � ���� ����� ������������ �������� �� ����� �� �������� ��������
     * @param index ������ �����, �� ������� ������� �����
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);
        //���� ���� ���������� � �� �� ����
        if (slot != null && slot.getHasStack()){
            //������� ���� �� �����
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            //��������������
            //���� ������ ����� ������ 12, �.�. ����� ������� �� ��������� ���� ��� ���� �����
            if (index < 12){
                //�������� ����������� ���� � ������ ��������� ���� � ������� ��� ���������, �.�. ����� 12 � 47 ������
                if (!this.mergeItemStack(itemstack1, 12, 48, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }

            //����� ��������. ���� ����� ������� �� ���� � ��������� ��� �������
            else if (index > 11){
                //���� ��� �����, �� �� ���� ����������� � ������ ���������� ��� ��� ���� ����� 8 � 11 ��������
                if(itemstack1.getItem() instanceof ItemArmor){
                    //��� ���� ������. ������ �������� 12 � �� 11? ������ ��� �� ������������. �.�. ����� 8 � 12 ������ �� ������������
                    if (!this.mergeItemStack(itemstack1, 8, 12, false)){
                        return ItemStack.EMPTY;
                    }
                }else
                    //���� ��� �� ����� � �� � ��������� �� �� � �������(�.�. ����� 12 � 38 ������), �� �������� ������� � ������, �.�. ����� 39 � 47 ������
                    if (index >= 12 && index < 39){
                        if (!this.mergeItemStack(itemstack1, 39, 48, false)){
                            return ItemStack.EMPTY;
                        }
                }else
                    //���� �� � �������(�.�. ����� 39 � 47 ������) �� �������� ����������� ������� � ���������(�.�. ����� � ������ ��������� ������ � ���������, �.�. ����� 12 � 38 ������)
                    if (index >= 39 && index < 48 && !this.mergeItemStack(itemstack1, 12, 39, false)){
                        return ItemStack.EMPTY;
                    }
            }
            //��������� ������� ��������
            if (itemstack1.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    /**
     * ����� �� ����� ����������������� � ����������?
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }











    public class ClothesSlot extends Slot {

        //��� �� ���� ����� �������� ���������� �����

        private final EntityPlayer thePlayer;
        private int removeCount;

        public ClothesSlot(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
            super(inventoryIn, slotIndex, xPosition, yPosition);
            this.thePlayer = player;
        }

        /* ����� �� ����� ���� ���� ������� � ���� ����. ����� ����� ���� ��������, �������� ���� �� ������
            ���� � ���� ������ ���� �������� ������, ���������� ����� �� ������� � ����� ������, ���� ��, ��
            ���������� false */
        public boolean isItemValid(@Nullable ItemStack stack){
            return (stack.getItem() instanceof IDressable && ((IDressable)stack.getItem()).getSlot() == getSlotIndex()) || (getSlotIndex()==0 && stack.getItem() instanceof ItemMask);
        }

        public ItemStack decrStackSize(int amount){
            if (this.getHasStack()){
                this.removeCount += Math.min(amount, this.getStack().getCount());
            }
            return super.decrStackSize(amount);
        }

        //��� ����������, ���� ������� ������� �� �����
        public ItemStack onTake(EntityPlayer player, ItemStack stack){
            this.onCrafting(stack);
            super.onTake(player, stack);
            return stack;
        }

        protected void onCrafting(ItemStack stack, int amount){
            this.removeCount += amount;
            this.onCrafting(stack);
        }

        protected void onCrafting(ItemStack stack){
            stack.onCrafting(this.thePlayer.world, this.thePlayer, this.removeCount);
            this.removeCount = 0;
        }
    }
}
