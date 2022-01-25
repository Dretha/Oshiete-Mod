package com.dretha.drethamod.entity.projectile;

import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.utils.OshieteDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {

    protected int damage = 4;
    protected Bullets bulletType;
    protected EntityLivingBase shooter;
    protected EntityLivingBase target;
    protected int patrTicksPre = 0;

    public EntityBullet(World world) {
        super(world);
    }

    public EntityBullet(World world, EntityLivingBase shooter, Bullets bulletType) {
        super(world, shooter);
        this.shooter = shooter;
        this.bulletType = bulletType;
        this.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, 4.5F, 1F);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (target!=null && !target.isDead && patrTicksPre+5>=target.ticksExisted) {
            //this.motionX = 0;
            //this.motionY = 0;
            //this.motionZ = 0;

            float f = 10F / 15.0F;
            float f1 = f * 0.6F + 0.4F;
            float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
            float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);

            this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, f1, f2, f3);
        } else if (target!=null && patrTicksPre+5<=target.ticksExisted) {
            this.setDead();
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit instanceof EntityLivingBase) {
                EntityLivingBase base = (EntityLivingBase) result.entityHit;

                patrTicksPre = base.ticksExisted;
                target = base;

                DamageSource source = OshieteDamageSource.causeBulletDamage(this, shooter);

                int savedResistantTime = base.hurtResistantTime;
                base.hurtResistantTime = 0;
                base.attackEntityFrom(source, damage);
                base.hurtResistantTime = savedResistantTime;
            }
        }
    }


}
