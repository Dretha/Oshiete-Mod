package com.dretha.drethamod.blocks.fusion_blast_furnace;

import com.dretha.drethamod.init.InitBlocks;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.main.Oshiete;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class FusionBlastFurnace extends Block implements ITileEntityProvider {

    public static final PropertyBool FIRE = PropertyBool.create("fire");

    public FusionBlastFurnace(String name)
    {
        super(Material.ROCK);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        setHardness(3.0F);
        setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FIRE, false));
        //setTickRandomly(true);

        InitBlocks.BLOCKS.add(this);
    }

    public void updateTick(World world, IBlockState state, BlockPos pos, Random random) {
        this.updateTick(world, pos, state, random);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean b = getTileEntity(worldIn, pos).isBurning();
        return state.withProperty(FIRE, b);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FIRE});
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        worldIn.setBlockState(pos, iblockstate.withProperty(FIRE, active), 3);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(InitBlocks.FUSION_BLAST_FURNACE);
    }



    @Override
    public boolean hasTileEntity(IBlockState blockState) {

        return true;
    }

    public Class<TileEntityFusionBlastFurnace> getTileEntityClass() {

        return TileEntityFusionBlastFurnace.class;
    }

    public TileEntityFusionBlastFurnace getTileEntity(IBlockAccess world, BlockPos position) {

        return (TileEntityFusionBlastFurnace) world.getTileEntity(position);
    }




    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState blockState, EntityPlayer playerSP, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {

            EntityPlayerMP player = (EntityPlayerMP) playerSP;
            TileEntityFusionBlastFurnace tileEntity = getTileEntity(world, position);

            player.openGui(Oshiete.instance, 1, player.getEntityWorld(), position.getX(), position.getY(), position.getZ());
        }

        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFusionBlastFurnace();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityFusionBlastFurnace)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, ((TileEntityFusionBlastFurnace)tileentity).getInventory());
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
}
