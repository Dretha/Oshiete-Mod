package com.dretha.drethamod.entity.projectile;

import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRCShard extends EntityArrow{

	private Block inTile;

	public EntityRCShard(World worldIn) {
		super(worldIn);
	}

	public EntityRCShard(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
	
	public EntityRCShard(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        this.shootingEntity = shooter;
        this.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, 3.0F, 1F);
        this.setIsCritical(false);
        this.setDamage(5);
        this.setKnockbackStrength(0);
        this.setFire(0);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;

        if (entity != null) {

            DamageSource damagesource = OshieteDamageSource.causeShardDamage(this, shootingEntity);

            if (this.isBurning() && !(entity instanceof EntityEnderman))
            {
                entity.setFire(5);
            }

            int savedResistantTime = entity.hurtResistantTime;
        	entity.hurtResistantTime = 0;
            if (entity.attackEntityFrom(damagesource, (float)super.getDamage()) && entity instanceof EntityLivingBase)
            {
            	entity.hurtResistantTime = savedResistantTime;

                EntityLivingBase base = (EntityLivingBase)entity;

                if (this.shootingEntity instanceof EntityLivingBase)
                {
                    EnchantmentHelper.applyThornEnchantments(base, this.shootingEntity);
                    EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, base);
                }

                if (this.shootingEntity != null && base != this.shootingEntity && base instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                }

                this.playSound(InitSounds.hit_of_kagune, 0.5F, 1.0F);

                PersonStats stats = PersonStats.getStats((EntityLivingBase) entity);
                if (stats != null) {
                    stats.addShardCountInEntity();
                }

                if (!(entity instanceof EntityEnderman))
                {
                    this.setDead();
                }
            }
            else
            {
            	motionX *= -0.10000000149011612D;
                motionY *= -0.10000000149011612D;
                motionZ *= -0.10000000149011612D;
                rotationYaw += 180.0F;
                prevRotationYaw += 180.0F;
                if (!world.isRemote && motionX * motionX + motionY * motionY + motionZ * motionZ < 0.0010000000474974513D) {
                    setDead();
                }
            }
        }
        else
        {
        	BlockPos blockpos = raytraceResultIn.getBlockPos();
            IBlockState iblockstate = world.getBlockState(blockpos);
            inTile = iblockstate.getBlock();
            inTile.getMetaFromState(iblockstate);
            motionX = (float)(raytraceResultIn.hitVec.x - posX);
            motionY = (float)(raytraceResultIn.hitVec.y - posY);
            motionZ = (float)(raytraceResultIn.hitVec.z - posZ);
            float f2 = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
            posX -= motionX / (double)f2 * 0.05000000074505806D;
            posY -= motionY / (double)f2 * 0.05000000074505806D;
            posZ -= motionZ / (double)f2 * 0.05000000074505806D;
            inGround = true;
            arrowShake = 7;
            setIsCritical(false);

            if (iblockstate.getMaterial() != Material.AIR) inTile.onEntityCollidedWithBlock(world, blockpos, iblockstate, this);
        }
    }

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
}
