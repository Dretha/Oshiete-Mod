package com.dretha.drethamod.init;

import com.dretha.drethamod.reference.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
   
public class InitSounds {
//sounds
	
    public static SoundEvent let_out_kagune = reg("let_out_kagune");
    public static SoundEvent hit_of_kagune = reg("hit_of_kagune");
    public static SoundEvent hit_air_kagune = reg("hit_air_kagune");
    public static SoundEvent hit_ground_kagune_2 = reg("hit_ground_kagune_2");
    public static SoundEvent ukaku_shooting = reg("ukaku_shooting");

    
    @SubscribeEvent
    public void regSound(RegistryEvent.Register<SoundEvent> e)
    {
        //Регистрация звука
        ForgeRegistries.SOUND_EVENTS.register(let_out_kagune);
        
    }
    
    @SubscribeEvent
    public void regSound2(RegistryEvent.Register<SoundEvent> e)
    {
        ForgeRegistries.SOUND_EVENTS.register(hit_of_kagune);
    }
    
    @SubscribeEvent
    public void regSound3(RegistryEvent.Register<SoundEvent> e)
    {
        ForgeRegistries.SOUND_EVENTS.register(hit_air_kagune);
    }
    
    @SubscribeEvent
    public void regSound4(RegistryEvent.Register<SoundEvent> e)
    {
        ForgeRegistries.SOUND_EVENTS.register(hit_ground_kagune_2);
    }
    
    @SubscribeEvent
    public void regSound5(RegistryEvent.Register<SoundEvent> e)
    {
        ForgeRegistries.SOUND_EVENTS.register(ukaku_shooting);
    }
    

    //Упрощённая регистрация звука
    public static SoundEvent reg(String name)
    {
        ResourceLocation rl = new ResourceLocation(Reference.MODID, name);
        return new SoundEvent(rl).setRegistryName(rl);
    }
}
