package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.init.InitSounds;
import net.minecraft.util.SoundEvent;

public enum Weapons
{
    KNIFE(0.649F, 0.5F, -1F, 5, 24, 3, InitSounds.sweep_light, InitSounds.hit_of_kagune),
    KATANA(1.149F, 0.7F, -1.9F, 8, 48, 6, InitSounds.sweep_medium, InitSounds.hit_of_kagune),
    SCYTHE(1.249F, 0.9F, -2.3F, 9, 64, 8, InitSounds.sweep_light, InitSounds.hit_of_kagune),
    CLEAVER(1F, 1F, -2.8F, 14, 128, 10, InitSounds.sweep_medium, InitSounds.hit_of_kagune),
    CUDGEL(1.3F, 1.2F, -3.35F, 26, 256, 16, InitSounds.sweep_heavy, InitSounds.hit_ground_kagune_2);

    public final float damageMultiplier;
    public final float blockMultiplier;
    public final float attackSpeed;
    public final int attackTick;
    public final int hardness;
    public final int shards;
    public final SoundEvent soundAir;
    public final SoundEvent soundAttack;

    Weapons(float damageMultiplier, float blockMultiplier, float attackSpeed, int attackTick, int hardness, int shards, SoundEvent soundAir, SoundEvent soundAttack)
    {
        this.damageMultiplier = damageMultiplier;
        this.blockMultiplier = blockMultiplier;
        this.attackSpeed = attackSpeed;
        this.attackTick = attackTick;
        this.hardness = hardness;
        this.shards = shards;
        this.soundAir = soundAir;
        this.soundAttack = soundAttack;
    }
}
