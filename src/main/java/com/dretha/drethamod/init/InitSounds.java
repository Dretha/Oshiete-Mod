package com.dretha.drethamod.init;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class InitSounds {

    private static final ArrayList<SoundEvent> SOUNDS = new ArrayList<>();
	
    public static SoundEvent let_out_kagune = initialize("let_out_kagune");
    public static SoundEvent hit_of_kagune = initialize("hit_of_kagune");
    public static SoundEvent hit_air_kagune = initialize("hit_air_kagune");
    public static SoundEvent hit_ground_kagune_2 = initialize("hit_ground_kagune_2");
    public static SoundEvent ukaku_shooting = initialize("ukaku_shooting");
    public static SoundEvent hk33shoot = initialize("hk33shoot");
    public static SoundEvent hk33replace = initialize("hk33replace");
    public static SoundEvent hk33reload = initialize("hk33reload");
    public static SoundEvent kuinke_block = initialize("kuinke_block");
    public static SoundEvent kuinke_hit = initialize("kuinke_hit");
    public static SoundEvent sweep_medium = initialize("sweep_medium");
    public static SoundEvent sweep_light = initialize("sweep_light");
    public static SoundEvent sweep_heavy = initialize("sweep_heavy");

    
    @SubscribeEvent
    public void registerSound(RegistryEvent.Register<SoundEvent> e)
    {
        for (SoundEvent sound : SOUNDS) {
            ForgeRegistries.SOUND_EVENTS.register(sound);
        }
    }

    public static SoundEvent initialize(String name)
    {
        ResourceLocation rl = new ResourceLocation(Reference.MODID, name);
        SoundEvent sound =  new SoundEvent(rl).setRegistryName(rl);
        SOUNDS.add(sound);
        return sound;
    }
}
