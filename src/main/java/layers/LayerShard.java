package com.dretha.drethamod.layers;

import java.util.Random;

import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class LayerShard implements LayerRenderer<EntityPlayer>
{
   @Override
   public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	   int i = EventsHandler.getCapaMP(player).getShardCountInEntity();
		
       if (i > 0)
       {
           Entity entity = new EntityRCShard(player.world, player.posX, player.posY, player.posZ);
           Random random = new Random((long)player.getEntityId());
           RenderHelper.disableStandardItemLighting();

           for (int j = 0; j < i; ++j)
           {
               GlStateManager.pushMatrix();
               GlStateManager.disableLighting();
               OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
               RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)player).getSkinType());
               ModelRenderer modelrenderer = renderPlayer.getMainModel().getRandomModelBox(random);
               ModelBox modelbox = modelrenderer.cubeList.get(random.nextInt(modelrenderer.cubeList.size()));
               modelrenderer.postRender(0.0625F);
               float f = random.nextFloat();
               float f1 = random.nextFloat();
               float f2 = random.nextFloat();
               float f3 = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f) / 16.0F;
               float f4 = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f1) / 16.0F;
               float f5 = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f2) / 16.0F;
               GlStateManager.translate(f3, f4, f5);
               f = f * 2.0F - 1.0F;
               f1 = f1 * 2.0F - 1.0F;
               f2 = f2 * 2.0F - 1.0F;
               f = f * -1.0F;
               f1 = f1 * -1.0F;
               f2 = f2 * -1.0F;
               float f6 = MathHelper.sqrt(f * f + f2 * f2);
               entity.rotationYaw = (float)(Math.atan2((double)f, (double)f2) * (180D / Math.PI));
               entity.rotationPitch = (float)(Math.atan2((double)f1, (double)f6) * (180D / Math.PI));
               entity.prevRotationYaw = entity.rotationYaw;
               entity.prevRotationPitch = entity.rotationPitch;
               double d0 = 0.0D;
               double d1 = 0.0D;
               double d2 = 0.0D;
               renderPlayer.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
               GlStateManager.enableLighting();
               GlStateManager.popMatrix();
           }

           RenderHelper.enableStandardItemLighting();
       }
   }

   @Override
   public boolean shouldCombineTextures()
   {
       return true;
   }
}