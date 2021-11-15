package com.dretha.drethamod.utils.handlers;

import java.util.Collections;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.KaguneGeoRenderer;
import com.dretha.drethamod.client.geckolib.kagunes.ModelKagune;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class KaguneEventsHandler {
	
	@SubscribeEvent
    public static void ukakuShooting(MouseEvent e) { 
    	if (e.getButton()==1 && EventsHandler.getCapaMP(Minecraft.getMinecraft().player).getGhoulType()==1 && EventsHandler.getCapaMP(Minecraft.getMinecraft().player).isKaguneActive()) {
    		EventsHandler.getCapaMP(Minecraft.getMinecraft().player).changeUkakuShooting();
    	}
    }
    private static float arrowVelocity = 1F;
	private static int shootSpeed = 3;
    @SubscribeEvent
    public static void ukakuShooting1(PlayerTickEvent e) { 
    	if (EventsHandler.getCapaMP(e.player).isUkakuShooting()) {
    		EntityPlayer player = e.player;
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
	        if (!player.world.isRemote && (player.ticksExisted%shootSpeed)==0 && capa.getRClevel()>=15) {
	    	     
	            EntityRCShard entityrcshard = new EntityRCShard(player.world, player);
	            entityrcshard.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, arrowVelocity * 3F, 1.0F);
	            entityrcshard.setIsCritical(false);
	            entityrcshard.setDamage(0);
	            entityrcshard.setKnockbackStrength(0);
	            entityrcshard.setFire(0);     
	                       
	            player.world.spawnEntity(entityrcshard);
	            
	            player.world.playSound(null, player.getPosition(), InitSounds.ukaku_shooting, SoundCategory.PLAYERS, 0.5F, 1.0F);
	            if (!((EntityPlayer) player).isCreative()) {
	                capa.removeRClevel(15);
	            }
	            
	        }
    	}
    }
    
    
    
    
    
}
