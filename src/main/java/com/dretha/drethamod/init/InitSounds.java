package com.dretha.drethamod.init;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
   
public class InitSounds {
//sounds
	
    public static SoundEvent let_out_kagune = reg("let_out_kagune");
    public static SoundEvent hit_of_kagune = reg("hit_of_kagune");
    public static SoundEvent hit_air_kagune = reg("hit_air_kagune");
    public static SoundEvent hit_ground_kagune_2 = reg("hit_ground_kagune_2");
    public static SoundEvent ukaku_shooting = reg("ukaku_shooting");
    public static SoundEvent hk33shoot = reg("hk33shoot");
    public static SoundEvent hk33replace = reg("hk33replace");
    public static SoundEvent hk33reload = reg("hk33reload");
    public static SoundEvent kuinke_block = reg("kuinke_block");
    public static SoundEvent kuinke_hit = reg("kuinke_hit");
    public static SoundEvent kuinke_air = reg("kuinke_air");

    
    @SubscribeEvent
    public void regSound(RegistryEvent.Register<SoundEvent> e)
    {
        //Регистрация звука
        ForgeRegistries.SOUND_EVENTS.register(let_out_kagune);
        ForgeRegistries.SOUND_EVENTS.register(hit_of_kagune);
        ForgeRegistries.SOUND_EVENTS.register(hit_air_kagune);
        ForgeRegistries.SOUND_EVENTS.register(hit_ground_kagune_2);
        ForgeRegistries.SOUND_EVENTS.register(ukaku_shooting);
        ForgeRegistries.SOUND_EVENTS.register(hk33shoot);
        ForgeRegistries.SOUND_EVENTS.register(hk33replace);
        ForgeRegistries.SOUND_EVENTS.register(hk33reload);
        ForgeRegistries.SOUND_EVENTS.register(kuinke_block);
        ForgeRegistries.SOUND_EVENTS.register(kuinke_hit);
        ForgeRegistries.SOUND_EVENTS.register(kuinke_air);
    }
    

    //Упрощённая регистрация звука
    public static SoundEvent reg(String name)
    {
        ResourceLocation rl = new ResourceLocation(Reference.MODID, name);
        return new SoundEvent(rl).setRegistryName(rl);
    }
}
