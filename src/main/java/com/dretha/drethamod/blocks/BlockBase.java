package com.dretha.drethamod.blocks;

import com.dretha.drethamod.init.InitBlocks;
import com.dretha.drethamod.items.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockBase extends Block {

    public BlockBase(String name, Material material, float hardness, float resistance)
    {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        setHardness(hardness);
        setHarvestLevel("pickaxe", 1);
        setResistance(resistance);

        InitBlocks.BLOCKS.add(this);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
}
