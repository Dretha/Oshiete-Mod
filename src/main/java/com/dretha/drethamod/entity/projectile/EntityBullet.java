package com.dretha.drethamod.entity.projectile;

import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.items.firearm.Bullets;
import com.dretha.drethamod.utils.OshieteDamageSource;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {

    protected Bullets bulletType;
    protected EntityLivingBase shooter;

    public EntityBullet(World world) {
        super(world);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        for (int k = 0; k < 4; ++k)
        {
            this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double)k / 4.0D, this.posY + this.motionY * (double)k / 4.0D, this.posZ + this.motionZ * (double)k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
        }
    }

    public EntityBullet(World world, EntityLivingBase shooter, Bullets bulletType) {
        super(world, shooter);
        this.shooter = shooter;
        this.bulletType = bulletType;
        this.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, 4.5F, 1F);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) result.entityHit;
                PersonStats stats = PersonStats.getStats(target);
                if ((stats!=null && stats.isGhoul() && bulletType.hurtGhoul() && stats.rank()<3) || stats==null || !stats.isGhoul()) {
                    DamageSource source = OshieteDamageSource.causeBulletDamage(this, shooter);
                    int savedResistantTime = target.hurtResistantTime;
                    target.hurtResistantTime = 0;
                    target.attackEntityFrom(source, bulletType.getDamage());
                    target.hurtResistantTime = savedResistantTime;
                } else {
                    target.world.playSound(null, target.getPosition(), InitSounds.rebound, SoundCategory.AMBIENT, 1.0F, 1.0F);
                    setDead();
                }
            }
        }
    }


}
