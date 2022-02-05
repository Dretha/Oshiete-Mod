package com.dretha.drethamod.layers.entity;

import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.render.RenderHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityLayerKakugan implements LayerRenderer<EntityHuman> {

	private final RenderLivingBase<?> renderer;
	
	public EntityLayerKakugan(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
    }

	@Override
	public void doRenderLayer(EntityHuman ghoulSP, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		EntityHuman ghoul = null;
    	try {
    	ghoul = EventsHandler.getHumanMP(ghoulSP);
    	} catch (IndexOutOfBoundsException e) {return;}
		   
		   if (ghoul.isKakuganActive()) {
			                                                                                  
			   RenderHuman renderHuman = (RenderHuman) renderer;
			   GlStateManager.pushMatrix();
		       
		       GlStateManager.disableLighting();
			   OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);

	           ((ModelBiped)renderHuman.getMainModel()).bipedHead.postRender(0.0625F);
	       
	           GlStateManager.translate(/*не трогать*/0, /*вниз*/0.1875F, /*вперед*/-0.02F);
	           GlStateManager.rotate(180, 0, 0, 0);
	           GlStateManager.scale(0.5F, 0.5F, 0.5F);

			   try {
				   Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ghoulSP.getKakugan()), ghoul, ItemCameraTransforms.TransformType.HEAD, false);
			   } catch (Exception e) {

			   }
	           
	           
	           GlStateManager.enableLighting();
	           
	           
	           GlStateManager.popMatrix();
		   }
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
