package layers.kagune;

import java.util.Collections;

import javax.annotation.Nonnull;

import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.EnumKagune;
import com.dretha.drethamod.utils.enums.UkakuState;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LayerKagune implements LayerRenderer<EntityPlayer> {
	
	private final RenderManager renderManager;


    public LayerKagune()
    {
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }

    public void doRenderLayer(@Nonnull EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	ICapaHandler capa = EventsHandler.getCapaMP(player);
        if (capa.isKaguneActive() && capa.getKagune()!=null) {
        	if (capa.ukaku() && !UkakuState.haveLimb(capa)) return;
        	
        	GlStateManager.enableRescaleNormal();
        	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
        	try {
				this.renderKagune(player, capa, EventsHandler.getCapaMP(player).getGhoulType().index()+1, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
        
        	GlStateManager.disableRescaleNormal();
        }
    }

    private void renderKagune(EntityPlayer player, ICapaHandler capa, int ghoulType, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, boolean b) throws CloneNotSupportedException
    {

        Item mask = capa.getInventory().getStackInSlot(0).getItem();

        if (mask != Items.AIR) {

            RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)player).getSkinType());
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            renderPlayer.getMainModel().bipedHead.postRender(0.0625F);

            GlStateManager.translate(/*не трогать*/0, (player.isSneaking() ? 0.2F : 0.0F) + /*вниз*/0.1875F, /*вперед*/-0.045F);
            GlStateManager.rotate(180, 0, 0, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(mask), player, ItemCameraTransforms.TransformType.HEAD, false);


            GlStateManager.popMatrix();
        }


    	AnimatedGeoModel kaguneModel = EnumKagune.valueOf(capa.getEnumId()).getModel(capa.getTextureLocation());
    	GeoEntityRenderer<EntityKagune> kaguneRenderer = EnumKagune.valueOf(capa.getEnumId()).getRender(renderManager);

        EntityKagune kagune;
        do {
        	kagune = EventsHandler.getCapaMP(player).getKagune();
        } while (EventsHandler.getCapaMP(player).getKagune()==null);
        kagune.setListF(limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        
        
        ResourceLocation kaguneTexture = kaguneModel.getTextureLocation(kagune);
        GeoModel geomodelkagune = kaguneModel.getModel(kaguneModel.getModelLocation(kagune));    
        
        kaguneRenderer.bindTexture(kaguneTexture);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        
        
        float heightPoint = (player.isSneaking() ? 0.2F : 0.0F);
        GlStateManager.translate(0.0F, heightPoint, 0.0F);
        GlStateManager.rotate(180, 0, 0, 1);
        
        EntityModelData entityModelData = new EntityModelData();
		entityModelData.isSitting = false;
		entityModelData.isChild = false;
		entityModelData.headPitch = -headPitch;
		entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent<EntityKagune> predicate = new AnimationEvent<>(kagune, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        kaguneModel.setLivingAnimations(kagune, kaguneRenderer.getUniqueID(kagune), predicate);
        kaguneRenderer.render(geomodelkagune, kagune, partialTicks, 1F, 1F, 1F, 1F);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();





    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}
