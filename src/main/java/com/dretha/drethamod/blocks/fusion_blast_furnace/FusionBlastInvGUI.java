package com.dretha.drethamod.blocks.fusion_blast_furnace;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class FusionBlastInvGUI extends GuiContainer {

    private final InventoryPlayer playerInventory;
    private final IInventory iInventory;
    private final TileEntityFusionBlastFurnace tile;

    public static final ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/fusion_blast_furnace_inventory.png");

    public FusionBlastInvGUI(EntityPlayer player, InventoryPlayer inventoryPlayer, TileEntityFusionBlastFurnace tile) {
        super(new FusionBlastContainer(player, inventoryPlayer, tile.getInventory()));
        this.playerInventory = inventoryPlayer;
        this.iInventory = tile.getInventory();
        this.tile = tile;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.iInventory.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INVENTORY_GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        //gui
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        //fire icon
        if (tile.isBurning());
        {
            int k = this.getBurnLeftScaled(14);
            this.drawTexturedModalRect(i + 24, j + 42 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        //progress bar
        if (tile.isCooking())
        {
            int l = this.getCookProgressScaled(34);
            this.drawTexturedModalRect(i + 97, j + 29, 176, 14, l, 21);
        }
    }

    private int getCookProgressScaled(int pixels)
    {
        return (int) (pixels*tile.getCookingProgress());
    }

    private int getBurnLeftScaled(int pixels)
    {
        return (int) (pixels*tile.getBurnRemaind());
    }
}
