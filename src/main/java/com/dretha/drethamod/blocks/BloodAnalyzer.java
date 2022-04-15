package com.dretha.drethamod.blocks;

import com.dretha.drethamod.blocks.tile.TileEntityBloodAnalyzer;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.SoundPlayer;
import com.dretha.drethamod.utils.controllers.ActionController;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BloodAnalyzer extends BlockTileOrientable<TileEntityBloodAnalyzer>{

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool PAPER = PropertyBool.create("paper");
    public final ActionController controller = new ActionController(3);

    public BloodAnalyzer(String name) {
        super(name, Material.IRON, 3.0F, 120F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(PAPER, false));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItemMainhand();
        boolean paperIsFill = state.getValue(PAPER);
        if (stack.getItem() == InitItems.BLOOD_SYRINGE && paperIsFill) {
            stack.shrink(1);
            world.setBlockState(pos, state.withProperty(PAPER, false), 2);
            player.addItemStackToInventory(new ItemStack(InitItems.EMPTY_SYRINGE));
            player.inventoryContainer.detectAndSendChanges();
            ItemStack bloodTest = new ItemStack(InitItems.BLOOD_TEST);
            bloodTest.setTagCompound(stack.getTagCompound());

            TileEntityBloodAnalyzer tileEntity = getTileEntity(world, pos);
            tileEntity.startPrint(bloodTest);
            return true;
        }

        if (stack.getItem()== Items.PAPER && !world.isRemote) {
            if (!paperIsFill) {
                stack.shrink(1);
                SoundPlayer.play(player, SoundEvents.BLOCK_CLOTH_BREAK);
                world.setBlockState(pos, state.withProperty(PAPER, true), 2);
            }
            return true;
        }

        return false;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultState(worldIn, pos, state);
    }

    private void setDefaultState(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(PAPER, false), 2);
        }
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(PAPER, false), 2);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(PAPER, false);
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING))).withProperty(PAPER, false);
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        boolean paperIsFill = state.getValue(PAPER);
        if (paperIsFill) {
            if (!world.isRemote)
                world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, new ItemStack(Items.PAPER)));
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, PAPER);
    }

    @Override
    public Class<TileEntityBloodAnalyzer> getTileEntityClass() {
        return TileEntityBloodAnalyzer.class;
    }

    @Nullable
    @Override
    public TileEntityBloodAnalyzer createTileEntity(World world, IBlockState blockState) {
        return new TileEntityBloodAnalyzer();
    }
}
