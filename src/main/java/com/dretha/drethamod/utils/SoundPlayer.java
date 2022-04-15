package com.dretha.drethamod.utils;

import net.minecraft.client.audio.ISound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoundPlayer
{
    public static void play(World world, SoundEvent soundEvent, BlockPos pos) {
        if (!world.isRemote)
            world.playSound(null, pos, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
    public static void play(EntityLivingBase entity, SoundEvent soundEvent) {
        if (!entity.world.isRemote)
            entity.world.playSound(null, entity.getPosition(), soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
    public static void play(EntityLivingBase entity, SoundEvent soundEvent, float volume) {
        if (!entity.world.isRemote)
            entity.world.playSound(null, entity.getPosition(), soundEvent, SoundCategory.PLAYERS, volume, 1.0F);
    }

    public static boolean soundEquals(ISound iSound, SoundEvent soundEvent) {
        return iSound.getSoundLocation().getResourcePath().equals(soundEvent.getSoundName().getResourcePath());
    }
}
