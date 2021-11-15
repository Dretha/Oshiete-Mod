package com.dretha.drethamod.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEnderArrow extends EntityArrow {
	
    public EntityEnderArrow(World world) {
        super(world);
        setDamage(1D);
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;

        this.setSize(0.5F, 0.5F);
    }

    public EntityEnderArrow(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
    }

    public EntityEnderArrow(World world, EntityLivingBase shooter) {
        this(world, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
    }

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
}