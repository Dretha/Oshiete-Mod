package layers;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class LayerKakugan implements LayerRenderer<EntityPlayer> {
	
	@Override
	public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		ICapaHandler capa = EventsHandler.getCapaMP(player);
		   
		   if (capa.isKakuganActive()/* && capa.getInventory().getStackInSlot(0).getItem()== Items.AIR*/) {
			                                                                                  
		       RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)player).getSkinType());
		       GlStateManager.pushMatrix();
		       
		       GlStateManager.disableLighting();
			   OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);

	           renderPlayer.getMainModel().bipedHead.postRender(0.0625F);
	       
	           GlStateManager.translate(/*не трогать*/0, (player.isSneaking() ? 0.2F : 0.0F) + /*вниз*/0.1875F, /*вперед -0.025*/-0.02F);
	           GlStateManager.rotate(180, 0, 0, 0);
	           GlStateManager.scale(0.5F, 0.5F, 0.5F);
	       
	           Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(InitItems.KAKUGAN31), player, ItemCameraTransforms.TransformType.HEAD, false);
	           
	           
	           
	           GlStateManager.enableLighting();
	           
	           
	           GlStateManager.popMatrix();
		   }
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
