package com.dretha.drethamod.utils;

import com.dretha.drethamod.entity.projectile.EntityBullet;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import javax.annotation.Nullable;

public class OshieteDamageSource
{
    public static DamageSource causeKaguneDamage(EntityLivingBase ghoul)
    {
        return new EntityDamageSource("kagune", ghoul);
        //death.attack.kagune
    }

    public static DamageSource causeShardDamage(EntityRCShard shard, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("shard", shard, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeBulletDamage(EntityBullet bullet, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("bullet", bullet, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeKuinkeAttack(EntityLivingBase dove)
    {
        return new EntityDamageSource("kuinke", dove);
        //death.attack.kagune
    }


    public static boolean isRCorBulletDamage(DamageSource source) {
        return source.damageType.equals("kagune") || source.damageType.equals("shard") || source.damageType.equals("bullet") || source.damageType.equals("kuinke");
    }

    public static boolean isRCMelleDamage(DamageSource source) {
        return source.damageType.equals("kagune") || source.damageType.equals("kuinke");
    }
}
