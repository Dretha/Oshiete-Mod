package com.dretha.drethamod.blocks.tile;

import com.dretha.drethamod.blocks.BloodAnalyzer;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.ItemStackUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileEntityBloodAnalyzer extends TileEntity implements ITickable {

    private int printTime = 0;
    private ItemStack result = ItemStack.EMPTY;
    NBTTagCompound readCompound = null;

    public void startPrint(ItemStack stack) {
        result = stack;
        printTime = 100;
        world.playSound(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, InitSounds.printing, SoundCategory.BLOCKS, 1F, 1F, false);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        ItemStackUtils.writeToNBT(result, compound, "result");
        compound.setInteger("printTime", printTime);
        compound.setBoolean("paperIsFill", world.getBlockState(pos).getValue(BloodAnalyzer.PAPER));
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        result = ItemStackUtils.readFromNBT(compound, "result");
        printTime = compound.getInteger("printTime");
        readCompound = compound;
        super.readFromNBT(compound);
    }

    @Override
    public void update() {
        if (printTime>0) {
            printTime--;
            if (printTime==0) {
                if (!world.isRemote) {
                    EnumFacing facing = world.getBlockState(pos).getValue(BloodAnalyzer.FACING);

                    switch (facing) {
                        case EAST: {
                            world.spawnEntity(new EntityItem(world, pos.getX() + 1.5, pos.getY() + 0.5, pos.getZ() + 0.5, result));
                            break;
                        }
                        case SOUTH: {
                            world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1.5, result));
                            break;
                        }
                        case WEST: {
                            world.spawnEntity(new EntityItem(world, pos.getX() - 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, result));
                            break;
                        }
                        case NORTH: {
                            world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() - 0.5, result));
                            break;
                        }
                    }
                }
            }
        }
        if (readCompound!=null) {
            world.setBlockState(pos, world.getBlockState(pos).withProperty(BloodAnalyzer.PAPER, readCompound.getBoolean("paperIsFill")), 2);
            readCompound = null;
        }
    }
}
