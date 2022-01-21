package com.dretha.drethamod.client.gui;

import com.dretha.drethamod.blocks.fusion_blast_furnace.FusionBlastContainer;
import com.dretha.drethamod.blocks.fusion_blast_furnace.FusionBlastInvGUI;
import com.dretha.drethamod.blocks.fusion_blast_furnace.TileEntityFusionBlastFurnace;
import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.inventory.ClothesInventoryContainer;
import com.dretha.drethamod.client.inventory.ClothesInventoryGUI;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int INVENTORY_GUI_ID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	PersonStats stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
        if(ID == INVENTORY_GUI_ID) {
            return new ClothesInventoryContainer(player.inventory, stats.getInventory(), player);
        }
        TileEntityFusionBlastFurnace tile_entity = (TileEntityFusionBlastFurnace) world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 1) {
            return new FusionBlastContainer(player, player.inventory, tile_entity.getInventory());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
    {
        PersonStats stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();
        if(ID == INVENTORY_GUI_ID) {
            return new ClothesInventoryGUI(player, player.inventory, stats.getInventory());
        }
        TileEntityFusionBlastFurnace tile_entity = (TileEntityFusionBlastFurnace) world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 1) {
            return new FusionBlastInvGUI(player, player.inventory, tile_entity);
        }
        return null;
    }

}
