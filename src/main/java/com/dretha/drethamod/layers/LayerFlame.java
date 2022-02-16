package com.dretha.drethamod.layers;

import com.dretha.drethamod.client.geckolib.kagunes.EnumFlame;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LayerFlame implements LayerRenderer<EntityLivingBase> {
	// TODO решить вопрос укаку
   @Override
   public void doRenderLayer(EntityLivingBase base, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	   PersonStats stats = PersonStats.getStats(base);
	   //ukaku
	   if (stats!=null && stats.ukaku() && stats.isKaguneActive() && UkakuState.haveFlame(stats))
	   {
		   if (stats.getUkakuState()==UkakuState.FLAME) {
			   renderFlame(base, true, true, stats);
			   renderFlame(base, false, true, stats);
		   }
		   if (stats.getUkakuState()==UkakuState.FLAMELIMB) {
			   renderFlame(base, false, true, stats);
		   }
	   }
   }
   
   private void renderFlame(EntityLivingBase base, boolean right, boolean big, PersonStats stats) {
	   
	   String s = big+"";
	   Item flame = big ? /*big*/EnumFlame.valueOf(String.format(s.toUpperCase() + "%02d", stats.getTextureVariant())).getFlame() : /*small*/EnumFlame.valueOf(String.format(big + "%02d", stats.getTextureVariant())).getFlame();
       
	   GlStateManager.pushMatrix();
       GlStateManager.disableLighting();
       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
       
       if (right) {
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
       
       if(base.isSneaking())
       {
    	   GlStateManager.translate(0.0F, -0.2F, 0.0F);
       }
    	   
       Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(flame), base, ItemCameraTransforms.TransformType.FIXED, false);
       
       GlStateManager.enableLighting();
       
       
       GlStateManager.popMatrix();
   }

   @Override
   public boolean shouldCombineTextures() {
       return true;
   }
}
