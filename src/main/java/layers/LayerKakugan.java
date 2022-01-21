package layers;

import com.dretha.drethamod.entity.EntityHuman;
import com.dretha.drethamod.entity.render.RenderHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LayerKakugan implements LayerRenderer<EntityLivingBase> {

	private RenderLivingBase<?> renderer = null;

	public LayerKakugan(RenderLivingBase<?> rendererIn)
	{
		this.renderer = rendererIn;
	}
	public LayerKakugan(){}

	@Override
	public void doRenderLayer(EntityLivingBase base, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		PersonStats stats = PersonStats.getStats(base);

		if (stats!=null && stats.isKakuganActive()) {

			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);

			if (base instanceof EntityLiving && renderer != null)
			{
				RenderLiving renderHuman = (RenderLiving) renderer;
				((ModelBiped)renderHuman.getMainModel()).bipedHead.postRender(0.0625F);
			}
			else if (base instanceof EntityPlayer)
			{
				RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)base).getSkinType());
				renderPlayer.getMainModel().bipedHead.postRender(0.0625F);
			}

			ItemStack kakugan = stats.getKakugan();

			GlStateManager.translate(/*не трогать*/0, (base.isSneaking() ? 0.2F : 0.0F) + /*вниз*/0.1875F, /*вперед -0.025*/-0.02F);
			GlStateManager.rotate(180, 0, 0, 0);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);

			Minecraft.getMinecraft().getRenderItem().renderItem(kakugan, base, ItemCameraTransforms.TransformType.HEAD, false);

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
