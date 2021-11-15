package com.dretha.drethamod.utils.handlers;

import java.util.List;

import javax.annotation.Nullable;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.main.Main;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.TakeASlashMessage;
import com.dretha.drethamod.server.TakeAThrustMessage;
import com.dretha.drethamod.utils.enums.ImpactType;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(
		value = { Side.CLIENT, Side.SERVER },
		modid = Reference.MODID)
public class KeyEventsHandler {
	
	//key listener activate kagune
    @SubscribeEvent
    public void activateKagune(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_ACTIVATE_KAGUNE.isPressed()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		if (capa.isGhoul() && capa.canKaguneActivated(player.ticksExisted) && !(capa.isKaguneActive())) {
    			
    			capa.releaseKagune();
				capa.setKaguneActivatedTicksPre(player.ticksExisted);
    		}
    		if (capa.isGhoul() && capa.canKaguneActivated(player.ticksExisted) && capa.isKaguneActive()) {
    			
    			capa.admitKagune();
				capa.setKaguneActivatedTicksPre(player.ticksExisted);
    		}
    	}
    }
    
   
   AttributeModifier speedmode = new AttributeModifier("speedmode", 1.6, 2);
   //key listener activate ghoul speed mode
   @SubscribeEvent
   public void activateSpeedMode(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_ACTIVATE_GHOUL_SPEED_MODE.isPressed()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		if (capa.isGhoul() && capa.canSpeedModeActivated(player.ticksExisted) && !(capa.isSpeedModeActive())) {
    			
    			capa.setActivatedSpeedMode(true);
    			capa.setActivatedKakugan(true);
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedmode);
				capa.setSpeedModeTicksPre(player.ticksExisted);
    		}
    		if (capa.isGhoul() && capa.canSpeedModeActivated(player.ticksExisted) && capa.isSpeedModeActive()) {
    			
    			capa.setActivatedSpeedMode(false);
    			if (!capa.isKaguneActive())
    			capa.setActivatedKakugan(false);
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(speedmode.getID()));
				capa.setSpeedModeTicksPre(player.ticksExisted);
    		}
    	}
   }
	
   
   //key listener impact
   @SubscribeEvent
   public void impactcontroller(KeyInputEvent e) {
    	if (KeybindsRegister.KEY_HIT_KAGUNE.isKeyDown()) {
    		EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
    		ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
    		
    		if (capa.isKaguneActive() && capa.canUseHit(player.ticksExisted) && !(capa.getKagune().isHit())) {
    			capa.getKagune().setHit(true);
    			capa.setHitTicksPre(player.ticksExisted);
    			if (capa.getImpactType().equals(ImpactType.THRUST)) {
    				Main.NETWORK.sendToServer(new TakeAThrustMessage("foobar"));
    			} else {
        			Main.NETWORK.sendToServer(new TakeASlashMessage("fo1111obar"));
    			}
    		}
    	}
   }
   @SubscribeEvent
   public void impactticker(PlayerTickEvent e) {
	   ICapaHandler capa = e.player.getCapability(CapaProvider.PLAYER_CAP, null);
	   if (capa.isKaguneActive() && capa.getKagune()!=null && capa.getKagune().isHit()) {
		   if (capa.canUseHit(e.player.ticksExisted)) capa.getKagune().setHit(false);
	   }
   }
   
   
   //key listener impact
   @SubscribeEvent
   public void changeImpactMode(KeyInputEvent e) {
	   if (KeybindsRegister.KEY_HIT_MODE.isKeyDown()) {
		   EntityPlayer player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
   		   ICapaHandler capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
   		
   		   if (capa.isGhoul() && capa.getImpactModeTicksPre()+3<=player.ticksExisted) {
   			   capa.changeImpactType();
   			   capa.setImpactModeTicksPre(player.ticksExisted);
   		   }
	   }
   }
}
