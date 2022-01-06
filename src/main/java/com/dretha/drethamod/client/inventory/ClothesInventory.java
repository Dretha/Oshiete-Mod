package com.dretha.drethamod.client.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class ClothesInventory implements IInventory {

    //��������� ������� ������������ ���-�� ����������� ��������� ������. � ��� �� ����� 8
    public static final int SIZE = 4;

    /*������ � ������� �������� ��������. ���� �� ������ ������� � ���� � ��������� ���������, �� ����������� � ���� ������. ������ ������ ������
    ���� ����� ��, ��� � ���-�� ��������� ������, �.�. 8*/
    private NonNullList<ItemStack>inventory = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);

    //�����������
    public ClothesInventory() {}

    /**
     * ��� ���������. ����� ����� ������������ � GUI
     */
    @Override
    public String getName() {
        return "Clothes";
    }

    /**
     * ����� �� ��������� ���� ��������� ���. ��, �����.
     */
    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
         return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }

    /**
     * ���������� ������ ���������
     */
    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    /**
     * ���������� ItemStack ��� ����� �� ���������� �������
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return index >= 0 && index < this.inventory.size() ? (ItemStack)this.inventory.get(index) : ItemStack.EMPTY;
    }

    /**
     * ���������� ������ � ����������� � ��������� ����������
     * @return ������ � ����������� � ��������� ����������
     */
    public NonNullList<ItemStack> getStacks(){
        return this.inventory;
    }

    /**
     * ������� ������������ ���������� ��������� �� ����� ��������� � ���������� �� � ���� ������ ������.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);
        if (!itemstack.isEmpty()) {
            this.markDirty();
        }
        return itemstack;
    }

    /**
     * ������� ���� �� ���������� ����� � ���������� ���.
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
         if (!this.inventory.get(index).isEmpty()) {
                ItemStack itemstack = this.inventory.get(index);
                this.inventory.set(index, ItemStack.EMPTY);
                return itemstack;
         }else {
                return ItemStack.EMPTY;
         }
    }

    /**
     * ��������� ���� � ��������� ����
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }

    /**
     * ���������� true ���� ���� ��������� ������. ���� �� ������ - ���������� false
     */
    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                    return false;
             }
        }
        return true;
    }

    /**
     * ���������� ������������ ������ ����� � �����
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * ��� ���� ������. ��� �� �� ����
     */
    @Override
    public void markDirty() { }

    /**
     * ����� �� ����� ����������� ���� ���������
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    /**
     * ����� �� ���������� ������ �������� � ����� � ��������� ��������
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    //��� ��� ������ ���� ���� �� �����
    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    /**
     * ������� ���������
     */
    @Override
    public void clear() {
        this.inventory.clear();
    }

    /**
     * ���� ����� ����� ���������� ��� ���������� ���� � ��� ����, �����, �������� ��� ���������� �������� �� ������� �� ���������
     * @param compound
     */
    public void writeToNBT(NBTTagCompound compound) {
         ItemStackHelper.saveAllItems(compound, this.inventory);
    }

    /**
     * ���� ����� ����� ���������� ��� ������ ���� �� ��� ����. ������� �� ��� ���� ����� � ��������� ���������
     * @param compound
     */
    public void readFromNBT(NBTTagCompound compound) {
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    /**
     * �������� ����� �� ������� ��������� � ����. ������������ � PlayerEvent.Clone Event, �� �� ��������� ���������� ���������, �������� ����� ����� �������� ����� ������ � ���
     * @param inv
     */
    public void copy(ClothesInventory inv) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            inventory.set(i, (stack.isEmpty() ? ItemStack.EMPTY : stack.copy()));
        }
    }
}
