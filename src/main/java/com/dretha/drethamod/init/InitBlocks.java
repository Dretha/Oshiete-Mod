package com.dretha.drethamod.init;

import com.dretha.drethamod.blocks.BlockBase;
import com.dretha.drethamod.blocks.fusion_blast_furnace.FusionBlastFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class InitBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    public static Block FUSION_BLAST_FURNACE = new FusionBlastFurnace("fusion_blast_furnace");
    public static Block FIRECLAY_BRICK = new BlockBase("fireclay_brick", Material.ROCK, 3.0F, 60F);
    public static Block STEEL_BLOCK = new BlockBase("steel_block", Material.IRON, 3.0F, 120F);
    public static Block KUINKE_STEEL_BLOCK = new BlockBase("kuinke_steel_block", Material.IRON, 3.0F, 30F);

    public static void init()
    {
        for (Block block:BLOCKS)
        setRegister(block);

        //tile entities
        GameRegistry.registerTileEntity(((FusionBlastFurnace)(FUSION_BLAST_FURNACE)).getTileEntityClass(), FUSION_BLAST_FURNACE.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void initRender()
    {
        for (Block block:BLOCKS)
        setRender(block);
    }

    private static void setRegister(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
