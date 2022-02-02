package com.dretha.drethamod.utils;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoundPlayer
{
    public static void play(World world, SoundEvent soundEvent, BlockPos pos) {
        world.playSound(null, pos, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}
