package com.dretha.drethamod.layers.entity;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.EnumFlame;
import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityLayerFlame implements LayerRenderer<EntityHuman> {
	
	private final RenderLivingBase<?> renderer;
	
	public EntityLayerFlame(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
    }
	
	   @Override
	   public void doRenderLayer(EntityHuman ghoulSP, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		   
		   EntityHuman ghoul = null;
	       try {
	       ghoul = EventsHandler.getHumanMP(ghoulSP);
	       } catch (IndexOutOfBoundsException e) {return;}
		   
		   if (ghoul.ukaku() && ghoul.isKaguneActive() && UkakuState.haveFlame(ghoul)) {
			   
			   if (ghoul.ukakuState()==UkakuState.FLAME) {
				   renderFlame(ghoul, true, true);
				   renderFlame(ghoul, false, true/*small*/);
			   }
			   if (ghoul.ukakuState()==UkakuState.FLAMELIMB) {
				   renderFlame(ghoul, false, true);
			   }
		   }
	   }
	   
	   private void renderFlame(EntityHuman ghoul, boolean rightFlameRender, boolean big) {
		   
		   String s = big+"";
		   Item flame = big ? /*big*/EnumFlame.valueOf(String.format(s.toUpperCase() + "%02d", ghoul.getTextureVariant())).getFlame() : /*small*/EnumFlame.valueOf(String.format(big + "%02d", ghoul.getTextureVariant())).getFlame();
	       
		   GlStateManager.pushMatrix();
	       GlStateManager.disableLighting();
	       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
	       
	       if (rightFlameRender) {
	    	   GlStateManager.rotate(205, 0, 1, 0);
			   GlStateManager.rotate(10, 0, 0, 1);
			   GlStateManager.scale(2, 2, 0.5);
			   GlStateManager.rotate(180, 0, 0, 1);
		       GlStateManager.translate(-0.6, 0.4, -0.17);
	       } else {
	    	   GlStateManager.rotate(155, 0, 1, 0);
	    	   GlStateManager.rotate(350, 0, 0, 1);
	    	   GlStateManager.scale(2, 2, 0.5);
	    	   GlStateManager.rotate(180, 1, 0, 0);
	           GlStateManager.translate(-0.6, 0.4, 0.17);
	       }
	       
	       if(ghoul.isSneaking())
	       {
	    	   GlStateManager.translate(0.0F, -0.2F, 0.0F);
	       }
	    	   
	       Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(flame), ghoul, ItemCameraTransforms.TransformType.FIXED, false);
	       
	       GlStateManager.enableLighting();
	       
	       
	       GlStateManager.popMatrix();
	   }

	   @Override
	   public boolean shouldCombineTextures() {
	       return true;
	   }
	}

