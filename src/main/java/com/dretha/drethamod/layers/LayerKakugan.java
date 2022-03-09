package com.dretha.drethamod.layers;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class LayerKakugan implements LayerRenderer<EntityLivingBase> {

	private RenderLivingBase<?> renderer = null;

	public LayerKakugan(RenderLivingBase<?> rendererIn)
	{
		this.renderer = rendererIn;
	}
	public LayerKakugan(){}

	@Override
	public void doRenderLayer(EntityLivingBase base, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		PersonStats stats = PersonStats.getStats(base);

		if (stats!=null && stats.isKakuganActive()) {
			if (base instanceof EntityPlayer) {
				this.renderer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer) base).getSkinType());
			}
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
			this.renderer.bindTexture(stats.getKakuganResource());
			this.renderer.getMainModel().render(base, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
			GL11.glPopAttrib();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
