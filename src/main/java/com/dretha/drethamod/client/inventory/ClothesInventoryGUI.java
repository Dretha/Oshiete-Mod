package com.dretha.drethamod.client.inventory;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class ClothesInventoryGUI extends GuiContainer{

	public static final ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/clothes_inventory.png");
	
    private float oldMouseX;
    private float oldMouseY;

    /*Путь к текстуре - MOD_ID + :textures/gui/inventory_gui.png. У меня это src/main/resources/assets/MOD_ID/textures/gui/inventory_gui.png
    private static final ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation(TestMod.MOD_ID + ":textures/gui/inventory_gui.png");
    MOD_ID - идентификатор мода. То, что вы указали в аннотации @Mod(modid="MOD_ID"). У меня это значение храниться в переменной в главном классе TestMod,
    например: public static final String MOD_ID = "testmod";. И использую его: TestMod.MOD_ID*/
    public ClothesInventoryGUI(EntityPlayer player, InventoryPlayer inventoryPlayer, ClothesInventory cInventory) {
        super(new ClothesInventoryContainer(inventoryPlayer, cInventory, player));
    }

    /**
     * Рисуем все компоненты на экране
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }

    /**
     * Рисуем задний фон(т.е. все, что позади предметов)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        //Биндим текстуру INVENTORY_GUI_TEXTURE, а так же добавляем модельку игрока как в ванильном инвентаре
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INVENTORY_GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        GuiInventory.drawEntityOnScreen(i + 88, j + 46, 19, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);
    }


}
