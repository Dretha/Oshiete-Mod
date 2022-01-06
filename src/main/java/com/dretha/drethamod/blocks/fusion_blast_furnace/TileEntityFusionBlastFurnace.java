package com.dretha.drethamod.blocks.fusion_blast_furnace;

import com.dretha.drethamod.init.InitBlocks;
import com.dretha.drethamod.init.InitItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

import java.util.Random;

public class TileEntityFusionBlastFurnace extends TileEntity implements ITickable {

    Random rand = new Random();
    private final FusionBlastInventory fusionBlastInventory = new FusionBlastInventory();
    private int furnaceBurnTime=0;
    private int burnRemaind = 1;
    private int cookTime=0;
    private int cookTimeCurrent = 1;
    private boolean cooking = false;
    private ItemStack result;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        getInventory().writeToNBT(compound);

        compound.setInteger("furnaceBurnTime", this.furnaceBurnTime);
        compound.setInteger("burnRemaind", this.burnRemaind);
        compound.setInteger("cookTime", this.cookTime);
        compound.setInteger("cookTimeCurrent", this.cookTimeCurrent);
        compound.setBoolean("cooking", this.cooking);

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        getInventory().readFromNBT(compound);

        this.furnaceBurnTime = compound.getInteger("furnaceBurnTime");
        this.burnRemaind = compound.getInteger("burnRemaind");
        this.cookTime = compound.getInteger("cookTime");
        this.cookTimeCurrent = compound.getInteger("cookTimeCurrent");
        this.cooking = compound.getBoolean("cooking");

        super.readFromNBT(compound);
    }

    public FusionBlastInventory getInventory() {
        return fusionBlastInventory;
    }

    public boolean isBurning()
    {
        return furnaceBurnTime>0;
    }

    public boolean isCooking() {
        return cooking;
    }

    public float getCookingProgress() {
        return 1-(float)cookTime/(float)cookTimeCurrent;
    }

    public float getBurnRemaind() {
        return (float)furnaceBurnTime/(float)burnRemaind;
    }


    @Override
    public void update() {
        //if (!this.world.isRemote) {

            FusionBlastRecipe recipe = FusionBlastRecipeHandler.findRecipe(getInventory());
            if (recipe!=null && !cooking && ((getInventory().getStackInSlot(3).getItem() == recipe.getResult().getItem() && getInventory().getStackInSlot(3).getCount()+recipe.getResult().getCount()<=recipe.getResult().getItem().getItemStackLimit()) || getInventory().getStackInSlot(3).isEmpty() ))
            {
                //load fuel
                if (furnaceBurnTime==0 && !getInventory().getStackInSlot(2).isEmpty()) {
                    System.out.println("load fuel");
                    ItemStack fuel = getInventory().getStackInSlot(2);
                    furnaceBurnTime = TileEntityFurnace.getItemBurnTime(fuel) + 1;
                    burnRemaind = furnaceBurnTime;
                    getInventory().removeStackFromSlot(2);
                    if (fuel.getCount() > 1)
                        getInventory().setInventorySlotContents(2, new ItemStack(fuel.getItem(), fuel.getCount() - 1));
                } else if (furnaceBurnTime==0) {
                    return;
                }

                //start
                System.out.println("start");
                cooking=true;
                cookTime=recipe.getCookTime();
                cookTimeCurrent=cookTime;
                result=recipe.getResult();

            } else if (cooking && cookTime>0 && furnaceBurnTime>0) {
                //cooking
                System.out.println("cooking");
                cookTime--;

                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.pos.getX()+0.5D, this.pos.getY()+0.5D, this.pos.getZ()+0.5D, 0, 0, 0);

            } else if (cooking && cookTime == 0 && recipe!=null) {
                //finish
                System.out.println("finish");
                cooking = false;
                if (getInventory().getStackInSlot(0).getItem() == recipe.getIngredient1().getItem()) {
                    getInventory().decrStackSize(0, recipe.getIngredient1().getCount());
                    getInventory().decrStackSize(1, recipe.getIngredient2().getCount());
                } else {
                    getInventory().decrStackSize(0, recipe.getIngredient2().getCount());
                    getInventory().decrStackSize(1, recipe.getIngredient1().getCount());
                }
                getInventory().setInventorySlotContents(3, new ItemStack(recipe.getResult().getItem(), recipe.getResult().getCount()+getInventory().getStackInSlot(3).getCount()));
            }
            if (furnaceBurnTime>0) {
                //burn
                System.out.println("burn");
                furnaceBurnTime--;
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.pos.getX()+0.5D, this.pos.getY()+0.5D, this.pos.getZ()+0.5D, 0, 0, 0);
            } else if (recipe==null || furnaceBurnTime==0){
                //stoping
                System.out.println("stop");
                cooking = false;
                cookTime = 0;
            }
            System.out.println(furnaceBurnTime);
            System.out.println(cookTime);
            System.out.println(cooking);
            System.out.println(getCookingProgress());
        //}
        //if (!world.isRemote) {
            //IBlockState state = world.getBlockState(pos);
            //state = state.withProperty(FusionBlastFurnace.FIRE, isBurning());
            //state = state.cycleProperty(FusionBlastFurnace.FIRE);
            //world.setBlockState(pos, state, 3);
            //world.setBlockState(pos, state);
            //FusionBlastFurnace.setState(isBurning(), world, pos);
        //}

    }
}
