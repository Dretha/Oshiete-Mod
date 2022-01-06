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
     * Конструктор
     * @param playerInventory Инвентарь игрока
     * @param cInventory Кастомный инвентарь
     * @param player Игрок
     */
    public ClothesInventoryContainer(InventoryPlayer playerInventory, ClothesInventory cInventory, EntityPlayer player) {

        //Добавляем 8 кастомных слотов. Аргументы: игрок, инвентарь к которому они относятся, индекс слота, х координата, у координата
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 0, 54, 6));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 1, 54, 33));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 2, 108, 6));
        this.addSlotToContainer(new ClothesSlot(player, cInventory, 3, 108, 33));

        //Добавляем 27 ванильных слотов инвентаря игрока
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(playerInventory, j1 + (l + 1) * 9, 9 + j1 * 18, 54 + l * 18));
            }
        }

        //А так же добавляем 9 ванильных слотов в хотбар
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(playerInventory, i1, 9 + i1 * 18, 112));
        }
    }

    /**
     * Этот метод срабатывает когда игрок зажимает Шифт и кликает на слот с целью переместить предмет.
     * Здесь мы должны задать откуда и куда будут перемещаться предметы из слота по которому кликнули
     * @param index Индекс слота, на который кликнул игрок
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);
        //Если слот существует и он не пуст
        if (slot != null && slot.getHasStack()){
            //Достаем стак из слота
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            //Взаимодействие
            //Если индекс слота меньше 12, т.е. игрок уликнул на кастомный слот или слот брони
            if (index < 12){
                //Пытаемся переместить стак в ПЕРВЫЙ свободный слот в хотбаре или инвентаре, т.е. между 12 и 47 слотом
                if (!this.mergeItemStack(itemstack1, 12, 48, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }

            //Здесь наоборот. Если игрок кликнул на слот в инвентаре или хотбаре
            else if (index > 11){
                //Если это броня, то ее надо переместить в первый подходящий для нее слот между 8 и 11 индексом
                if(itemstack1.getItem() instanceof ItemArmor){
                    //тут один момент. Почему передаем 12 а не 11? Потому что не включительно. Т.е. между 8 и 12 слотом не включительно
                    if (!this.mergeItemStack(itemstack1, 8, 12, false)){
                        return ItemStack.EMPTY;
                    }
                }else
                    //Если это не броня и мы в инвентаре но не в хотбаре(т.е. между 12 и 38 слотом), то помещаем предмет в хотбар, т.е. между 39 и 47 слотом
                    if (index >= 12 && index < 39){
                        if (!this.mergeItemStack(itemstack1, 39, 48, false)){
                            return ItemStack.EMPTY;
                        }
                }else
                    //Если мы в хотбаре(т.е. между 39 и 47 слотом) то пытаемся переместить предмет в инвентарь(т.е. между в ПЕРВЫМ свободным слотом в инвентаре, т.е. между 12 и 38 слотом)
                    if (index >= 39 && index < 48 && !this.mergeItemStack(itemstack1, 12, 39, false)){
                        return ItemStack.EMPTY;
                    }
            }
            //Остальные простые проверки
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
     * Может ли игрок взаимодействовать с инвентарем?
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }











    public class ClothesSlot extends Slot {

        //Это по сути копия обычного ванильного слота

        private final EntityPlayer thePlayer;
        private int removeCount;

        public ClothesSlot(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
            super(inventoryIn, slotIndex, xPosition, yPosition);
            this.thePlayer = player;
        }

        /* Может ли даный стак быть положен в этот слот. Здесь могут быть проверки, например если вы хотите
            чтоб в слот нельзя было положить яблоко, проверяете равен ли предмет в стаке яблоку, если да, то
            возвращаем false */
        public boolean isItemValid(@Nullable ItemStack stack){
            return (stack.getItem() instanceof IDressable && ((IDressable)stack.getItem()).getSlot() == getSlotIndex()) || (getSlotIndex()==0 && stack.getItem() instanceof ItemMask);
        }

        public ItemStack decrStackSize(int amount){
            if (this.getHasStack()){
                this.removeCount += Math.min(amount, this.getStack().getCount());
            }
            return super.decrStackSize(amount);
        }

        //Что происходит, если забрать предмет из слота
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
